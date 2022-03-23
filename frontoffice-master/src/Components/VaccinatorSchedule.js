import React, { useEffect, useState } from 'react'
import Title from './Title'
import Table from './react-tailwind-table'
import { FcInfo } from 'react-icons/all'
import VaccinationPlanInfo from './VaccinationPlanInfo'
import Swal from '@sweetalert/with-react'
import VaccinationCenterInfo from './VaccinationCenterInfo'
import { Cookies } from 'react-cookie'
import { BlockLoading } from 'react-loadingg'
import swal from '@sweetalert/with-react'
import { Link } from 'react-router-dom'

const VaccinatorSchedule = () => {
  const [loading, setLoading] = useState(true)

  const c = new Cookies()

  const getCook = () => {
    if (c.get('__FOsession') !== undefined) {
      return c.get('__FOsession')
    }
  }

  const getCi = () => {
    if(c.get('__FOsession') !== undefined) {
      if (cook.ci.toString() !== undefined) {
        return cook.ci.toString()
      }
    }
  }

  const cook = getCook()

  const ci = getCi()

  // const cook = c.get('__FOsession')
  //
  // const ci = cook.ci.toString()

  const [vacScheduleData, setVacScheduleData] = useState([
    {
      scheduleId: '',
      vaccinationPlanId: '',
      vaccinationPlanName: '',
      vaccineName: '',
      vaccinationCenterId: '',
      vaccinationCenterName: '',
      vaccinationPostId: '',
      vaccinationPostName: '',
      dateStart: '',
      dateEnd: '',
      timeStart: '',
      timeEnd: '',
    },
  ])

  const vacScheduleColumnGridName = [
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
      field: 'scheduleId',
      //This will be used to display in the table heading
      use: 'Agenda',
      //Indicates that of this column should be used to search (optional)
      use_in_search: false,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: false,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationPlanId',
      //This will be used to display in the table heading
      use: 'vaccinationPlanId',
      //Indicates that of this column should be used to search (optional)
      use_in_search: false,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: false,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationPlanName',
      //This will be used to display in the table heading
      use: 'Plan de vacunación',
      //Indicates that of this column should be used to search (optional)
      use_in_search: true,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationCenterId',

      //This will be used to display in the table heading
      use: 'vaccinationCenterId',

      //Indicates that of this column should be used to search (optional)
      use_in_search: false,

      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: false,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationCenterName',
      //This will be used to display in the table heading
      use: 'Centro de vacunación',
      //Indicates that of this column should be used to search (optional)
      use_in_search: true,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationPostId',
      //This will be used to display in the table heading
      use: 'vaccinationPostId',
      //Indicates that of this column should be used to search (optional)
      use_in_search: false,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: false,
    },
    {
      //Used to get string data from the each row object
      field: 'vaccinationPostName',
      //This will be used to display in the table heading
      use: 'Puesto de vacunación',
      //Indicates that of this column should be used to search (optional)
      use_in_search: true,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'dateStart',
      //This will be used to display in the table heading
      use: 'Día de inicio',
      //Indicates that of this column should be used to search (optional)
      use_in_search: false,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'dateEnd',
      //This will be used to display in the table heading
      use: 'Día de fin',
      //Indicates that of this column should be used to search (optional)
      use_in_search: false,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'timeStart',
      //This will be used to display in the table heading
      use: 'Hora inicio',
      //Indicates that of this column should be used to search (optional)
      use_in_search: false,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
    {
      //Used to get string data from the each row object
      field: 'timeEnd',
      //This will be used to display in the table heading
      use: 'Hora fin',
      //Indicates that of this column should be used to search (optional)
      use_in_search: false,
      //Indicates If this property should be used displayed in the table header (optional)
      use_in_display: true,
    },
  ]

  async function getVacScheduleList() {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR =
      url + '/VacunasUYG16-api/api/vaccinator/getVaccinatorDatabyCi/' + ci

    const responseR = await fetch(urlR, {
      // crossDomain: true,
      mode: 'cors',
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const scheduleList = await responseR.json()

    setVacScheduleData(
      scheduleList.map((v) => ({
        scheduleId: v.scheduleId,
        vaccinationPlanId: v.vaccinationPlanId,
        vaccinationPlanName: v.vaccinationPlanName,
        vaccineName: v.vaccineName,
        vaccinationCenterId: v.vaccinationCenterId,
        vaccinationCenterName: v.vaccinationCenterName,
        vaccinationPostId: v.vaccinationPostId,
        vaccinationPostName: v.vaccinationPostName,
        dateStart: v.dateStart,
        dateEnd: v.dateEnd,
        timeStart: v.timeStart,
        timeEnd: v.timeEnd,
      })),
    )

    setLoading(false)
  }

  useEffect(async () => {
    if (c.get('__FOsession') !== undefined) {
      const cook = c.get('__FOsession')

      if (cook !== undefined) {
        if (cook.role.toString() !== 'Vaccinator') {
          //NO TIENE ROL PARA INGRESAR A ESTA VENTANA
          return swal(
            'Usted no tiene permisos para acceder a esta funcionalidad',
            'Será redirigido a la página principal',
            'error',
            { button: 'Continuar' },
          ).then((value) => {
            window.location.href = '/'
            return <Link to="./" />
          })
        }
      }
    } else {
      //NO TIENE SESIÓN HECHA
      return swal(
        'Usted no tiene permisos para acceder a esta funcionalidad',
        'Será redirigido a la página principal',
        'error',
        { button: 'Continuar' },
      ).then((value) => {
        window.location.href = '/'
        return <Link to="./" />
      })
    }

    await getVacScheduleList()
  }, [])

  const rowcheck = (row, column, display_value) => {
    if (column.field === 'vaccinationPlanName') {
      const vaccPlanId = row.vaccinationPlanId

      return (
        <div className="flex flex-row">
          <label>{row.vaccinationPlanName}</label>&nbsp;
          <FcInfo
            size="1.5em"
            cursor="pointer"
            title="Haga clic para visualizar la información del plan de vacunación"
            onClick={() =>
              Swal(<VaccinationPlanInfo vaccinationPlanId={vaccPlanId} />, {
                buttons: false,
              })
            }
          />
        </div>
      )
    }

    if (column.field === 'vaccinationCenterName') {
      const vaccCentId = row.vaccinationCenterId

      return (
        <div className="flex flex-row">
          <label>{row.vaccinationCenterName}</label>&nbsp;
          <FcInfo
            size="1.5em"
            cursor="pointer"
            title="Haga clic para visualizar la información del vacunatorio"
            onClick={() =>
              Swal(<VaccinationCenterInfo vaccinationCenterId={vaccCentId} />, {
                buttons: false,
              })
            }
          />
        </div>
      )
    }
  }

  return (
    <>
      <div className="grid justify-items-center">
        <Title title="Agendas asignadas al vacunador" />
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
        <div>
          <Table
            columns={vacScheduleColumnGridName}
            rows={vacScheduleData}
            per_page="10"
            no_content_text="No hay resultados para mostrar..."
            row_render={rowcheck}
          />
        </div>
      )}
    </>
  )
}

export default VaccinatorSchedule
