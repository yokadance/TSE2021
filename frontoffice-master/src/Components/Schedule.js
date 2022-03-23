import React, { useEffect, useState } from 'react'
import Table from './react-tailwind-table'
import Title from './Title'
import { FcCancel, FcInfo, FcOk } from 'react-icons/all'
import { BlockLoading } from 'react-loadingg'
import { Cookies } from 'react-cookie'
import swal from '@sweetalert/with-react'

const Schedule = () => {
  const [loading, setLoading] = useState(true)

  const [scheduleData, setScheduleData] = useState([
    {
      startDate: '',
      endDate: '',
      startTimeDaily: '',
      endTimeDaily: '',
      vaccinationPlan: '',
      vaccinationPlanId: '',
      vaccineName: '',
      vaccinationCenterName: '',
      saturday: false,
      sunday: false,
    },
  ])

  const c = new Cookies()

  const getCook = () => {
    if (c.get('__FOsession') !== undefined) {
      return c.get('__FOsession')
    } else {
      return null
    }
  }

  const getCi = () => {
    if (c.get('__FOsession') !== undefined) {
      if (cook.ci.toString() !== undefined) {
        return cook.ci.toString()
      }
    }
  }

  const cook = getCook()

  const ci = getCi()

  function checkRestriction(vacPlanId) {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlF =
      url +
      '/VacunasUYG16-api/api/restriction/ageRestriction/' +
      ci +
      '/' +
      vacPlanId

    const responseF = fetch(urlF, {
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const dataF = responseF

    if (dataF.status !== 200 && dataF.status !== 202) {
      //No está habilitado
      return false
    } else {
      return true
    }
  }

  const scheduleColumnGridName = [
    {
      //Used to get string data from the each row object
      field: 'vaccineName',

      //This will be used to display in the table heading
      use: 'Vacuna',

      //Indicates that of this column should be used to search (optional)
      use_in_search: true,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationCenterName',

      //This will be used to display in the table heading
      use: 'Vacunatorio',

      //Indicates that of this column should be used to search (optional)
      use_in_search: true,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationPlan',

      //This will be used to display in the table heading
      use: 'Plan de vacunación',

      //Indicates that of this column should be used to search (optional)
      use_in_search: true,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationPlanId',

      //This will be used to display in the table heading
      use: '',

      //Indicates that of this column should be used to search (optional)
      use_in_search: false,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: false,
    },
    {
      //Used to get string data from the each row object
      field: 'startDate',

      //This will be used to display in the table heading
      use: 'Fecha de inicio',

      //Indicates that of this column should be used to search (optional)
      use_in_search: false,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'endDate',

      //This will be used to display in the table heading
      use: 'Fecha de fin',

      //Indicates that of this column should be used to search (optional)
      use_in_search: false,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'saturday',

      //This will be used to display in the table heading
      use: '¿Sábado?',

      //Indicates that of this column should be used to search (optional)
      use_in_search: false,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'sunday',

      //This will be used to display in the table heading
      use: '¿Domingo?',

      //Indicates that of this column should be used to search (optional)
      use_in_search: false,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'restrictions',

      //This will be used to display in the table heading
      use: 'Restricciones',

      //Indicates that of this column should be used to search (optional)
      use_in_search: false,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
  ]

  const rowcheck = (row, column, display_value) => {
    if (column.field === 'saturday') {
      if (!row.saturday) {
        return (
          <div className="ml-5">
            <FcCancel
              size="2em"
              title="La agenda no está habilitada los días sábados"
            />
          </div>
        )
      } else {
        return (
          <div className="ml-5">
            <FcOk
              size="2em"
              title="La agenda está habilitada los días sábados"
            />
          </div>
        )
      }
    }

    if (column.field === 'sunday') {
      if (!row.sunday) {
        return (
          <div className="ml-6">
            <FcCancel
              size="2em"
              title="La agenda no está habilitada los días domingos"
            />
          </div>
        )
      } else {
        return (
          <div className="ml-6">
            <FcOk
              size="2em"
              title="La agenda está habilitada los días domingos"
            />
          </div>
        )
      }
    }

    if (column.field === 'restrictions') {
      if (cook === null) {
        //No ingresó en el sistema
        return (
          <div className="ml-7">
            <FcInfo
              size="2em"
              cursor="pointer"
              title="Este plan de vacunación cuenta con restricciones, haga clic para ver más"
              onClick={() =>
                // falta enganchar listado de restricciones
                swal(
                  'El plan de vacunación "' +
                    row.vaccinationPlan +
                    '" cuenta con las siguietes restrcciones: ',
                  // 'puedo poner texto más chico acá',
                  { buttons: false },
                )
              }
            />
          </div>
        )
      } else {
        //Está logeado en el sistema
        if (checkRestriction(row.vaccinationPlanId)) {
          //Está habilitado para el plan
          return (
            <div className="ml-7">
              <FcOk
                size="2em"
                cursor="pointer"
                title="Usted está habilitado para este plan de vacunación, haga clic para ver más"
                onClick={() =>
                  // falta enganchar listado de restricciones
                  swal(
                    'El plan de vacunación "' +
                      row.vaccinationPlan +
                      '" cuenta con las siguietes restrcciones: ',
                    // 'puedo poner texto más chico acá',
                    { buttons: false },
                  )
                }
              />
            </div>
          )
        } else {
          //No está habilitado
          return (
            <div className="ml-7">
              <FcCancel
                size="2em"
                cursor="pointer"
                title="Usted no está habilitado para este plan de vacunación, haga clic para ver más"
                onClick={() =>
                  // falta enganchar listado de restricciones
                  swal(
                    'El plan de vacunación "' +
                      row.vaccinationPlan +
                      '" cuenta con las siguietes restrcciones: ',
                    // 'puedo poner texto más chico acá',
                    { buttons: false },
                  )
                }
              />
            </div>
          )
        }
      }
    }

    return display_value
  }

  useEffect(() => {
    getScheduleList()
  }, [])

  async function getScheduleList() {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR = url + '/VacunasUYG16-api/api/schedule/getNextScchedules'

    const responseR = await fetch(urlR, {
      // crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const scheduleList = await responseR.json()

    setScheduleData(
      scheduleList.map((s) => ({
        startDate: s.startDate,
        endDate: s.endDate,
        startTimeDaily: s.startTimeDaily,
        endTimeDaily: s.endTimeDaily,
        vaccinationPlan: s.vaccinationPlan,
        vaccinationPlanId: s.vaccinationPlanId,
        vaccineName: s.vaccineName,
        vaccinationCenterName: s.vaccinationCenterName,
        saturday: s.saturday,
        sunday: s.sunday,
      })),
    )

    setLoading(false)
  }

  return (
    <>
      <div className="grid justify-items-center">
        <Title title="Agendas" strStyle="" />
      </div>
      {loading ? (
        <div className="w-full">
          <div className="grid justify-items-center mt-20">
            {/*<CircleLoading style={{ }} color='#75AFD6' size='large'/>*/}
            <BlockLoading style={{}} color="#75AFD6" size="large" />
            {/*<LoopCircleLoading style={{ }} color='#75AFD6' size='large'/>*/}
          </div>
        </div>
      ) : (
        <Table
          columns={scheduleColumnGridName}
          rows={scheduleData}
          per_page="10"
          no_content_text="No hay resultados para mostrar..."
          row_render={rowcheck}
        />
      )}
    </>
  )
}

export default Schedule
