import React, { useEffect, useState } from 'react'
import Swal from 'sweetalert2'
import Dropdown from './Dropdown'
import 'flatpickr/dist/themes/material_green.css'
import Flatpickr from 'react-flatpickr'
import 'flatpickr/dist/themes/material_blue.css'
import 'rc-time-picker/assets/index.css'
import { Cookies } from 'react-cookie'
import swal from '@sweetalert/with-react'
import { FcOk } from 'react-icons/all'

export default function FormAgendar({ onChange }) {
  const [reservationData, set_reservationData] = useState([
    {
      reservationId: '',
      vaccinationPlanName: '',
      vaccinationPlanId: 0,
      reservationCenterId: '',
      reservationCenterName: '',
      reservationDate: '',
      reservationTime: '',
      reservationState: '',
      vaccineName: '',
      vaccinationPostId: '',
      vaccinationPostName: '',
      doses: '',
    },
  ])

  const [arrayRestrictionsChecked, setArrayRestrictionsChecked] = useState([])
  // arrayRestrictionsChecked:
  //  [{
  //      'elementName': '',
  //      'description': '',
  //      'checked': false,
  //  }]

  const [checkPlan, setCheckPlan] = useState(false)
  const url = 'https://vacunasuyg16.web.elasticloud.uy'
  // const url = 'http://localhost:8080'

  /*----------------------Date picker----------------------*/

  const [stateDay, setStateDay] = useState(new Date())
  const { dateDay } = stateDay
  const [today, setToday] = useState(new Date())
  const [hourSelected, setHourSelected] = useState(null)
  const [minuteSelected, setMinuteSelected] = useState(null)

  function setCloseModal() {
    onChange(false)
  }

  const [fractionSchedule, SetFractionSchedule] = useState(0)

  async function getFraction() {
    const urlF = url + '/VacunasUYG16-api/api/schedule/listScheduleById/'
    const responseF = await fetch(urlF + selectedSchulde, {
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })
    const dataF = await responseF.json()
    SetFractionSchedule(dataF.fraction)

    console.log('Fraction: ' + dataF.fraction)

    minutesForSelect(dataF.fraction)
  }

  const [minutesDropdwon, SetMinutesDropdow] = useState(null)

  function minutesForSelect(fraction) {
    //armo array de minutos en base a la fraccion estipulada por el schedule
    const arrMinutes = []
    let min = 0
    var f = 60 / fraction
    for (var i = 1; i < f; i++) {
      min = min + fraction
      arrMinutes.push([min])
    }
    SetMinutesDropdow(arrMinutes)
    return arrMinutes
  }

  function hoursForSelect() {
    //armo array de horas para el plan seleccionado
    getHoursStart(selectedSchulde)
    let a = []
    let c = []
    a = hStart.split(':')
    c = hEnd.split(':')
    let start = parseInt(a[0])
    let startS = a[0]
    let end = parseInt(c[0])
    let arrH = []
    let arrM = []
    let t
    let allHours = allHoursNotAvaible
    for (var i = start; i <= end; i++) {
      for (var m = 0; m < minutesDropdwon.length; m++) {
        t = i.toString() + ':' + minutesDropdwon[m].toString() //formo una hora t con las horas no disponibles y los minutos en base a "fraccion"
        for (var r = 0; r < allHours.length; r++) {
          //verifico que el valor de t no este en horas no disponibles,
          if (allHours[r].date != t) {
            arrH.push(i)
            arrM.push(minutesDropdwon[m])
          }
        }
      }
    }
  }

  /*----------------------Fin Time picker----------------------*/

  const [valueVaccPlan, setValueVaccPlan] = useState(null)
  const [valueVaccCenter, setValueVaccCenter] = useState(null)
  const [valueDaySelect, setvalueDaySelect] = useState(null)

  const [_vacPlanNames, set_vacPlanNames] = useState([
    {
      id: '',
      name: '',
    },
  ])

  const c = new Cookies()
  const [ci, setCi] = useState('') //CI ACA

  //carga inicial de planes de vacunacion
  useEffect(() => {
    async function getReservationList(ci_aux) {
      const url = 'https://vacunasuyg16.web.elasticloud.uy'
      // const url = 'http://localhost:8080'

      const urlR =
        url + '/VacunasUYG16-api/api/Reservation/getReservationData/' + ci_aux
      const responseR = await fetch(urlR, {
        // crossDomain: true,
        mode: 'cors',
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        cache: 'default',
      })

      const reservationList = await responseR.json()

      set_reservationData(
        reservationList.map((r) => ({
          reservationId: r.reservationId,
          vaccinationPlanId: r.vaccinationPlanId,
          vaccinationPlanName: r.vaccinationPlanName,
          reservationCenterId: r.reservationCenterId,
          reservationCenterName: r.reservationCenterName,
          reservationDate: r.reservationDate,
          reservationTime: r.reservationTime,
          reservationState: r.reservationState,
          vaccineName: r.vaccineName,
          vaccinationPostId: r.vaccinationPostId,
          vaccinationPostName: r.vaccinationPostName,
          doses: r.doses,
        })),
      )
    }

    async function getVaccinationPlan() {
      const urlP =
        url + '/VacunasUYG16-api/api/vaccionationPlan/listVaccinationsPlans'
      const responseP = await fetch(urlP, {
        // crossDomain: true,
        mode: 'cors',
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        cache: 'default',
      })
      const dataPlan = await responseP.json()
      set_vacPlanNames(
        dataPlan.map((p) => ({
          id: p.id,
          name: p.name,
        })),
      )
    }

    getVaccinationPlan()

    const a = c.get('__FOsession')
    if (a !== undefined) {
      setCi(a.ci.toString())
      getReservationList(a.ci.toString())
    }
  }, [])

  //aca me traigo los vacunatorios disponibles para ese plan elegido -OK

  const [_vaccinationCenterAvaible, set_vaccinationCenterAvaible] = useState([
    {
      id: '',
      name: '',
    },
  ])

  async function getVaccinationCenterForSelectedPlan(value) {
    const urlP =
      url +
      '/VacunasUYG16-api/api/vaccionationPlan/vaccinationCentersByVaccinationPlan/'
    const responseVc = await fetch(urlP + value, {
      // crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })
    const dataVaccCenter = await responseVc.json()
    set_vaccinationCenterAvaible(
      dataVaccCenter.map((p) => ({
        id: p.id,
        name: p.name,
      })),
    )
  }

  //me traigo el dia de fin de ese plan de vacunacion para limitar el calendario - OK
  const [_daysEndSchulde, set_daysEndSchulde] = useState([
    {
      endDate: '',
    },
  ])

  async function getDaysForVaccPlan(value) {
    const urlD =
      url + '/VacunasUYG16-api/api/vaccionationPlan/listVaccinationPlanById/'
    const responseD = await fetch(urlD + value, {
      // crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })
    const dataD = await responseD.json()
    set_daysEndSchulde(dataD.endDate)
    return _daysEndSchulde
  }

  //retorna el id de las agendas de ese  vacunatorio seleccionado y que esten vigentes

  const [_idSchedule, set_idSchedule] = useState([
    {
      id: '',

      endDate: '',
      vaccinationPlan: {
        id: '',
      },
      vaccinationCenter: {
        id: '',
      },
    },
  ])

  const [selectedSchulde, setSelectedSchulde] = useState(0)

  async function getIdSchedule(valueVcenter) {
    const urlI = url + '/VacunasUYG16-api/api/schedule/schedulesbyVCandVP/'
    // const responseIdS = await fetch(urlI + valueVcenter + '/' + valueVaccPlan, {
    const responseIdS = await fetch(urlI + valueVaccPlan + '/' + valueVcenter, {
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })
    const dataIdSchedule = await responseIdS.json()
    set_idSchedule(
      dataIdSchedule.map((s) => ({
        id: s.id,
        endDate: s.endDate,
        vaccinationPlan: {
          id: s.vaccinationPlan.id,
        },
        vaccinationCenter: {
          id: s.vaccinationCenter.id,
        },
      })),
    )
    let m
    for (let j = 0; j < dataIdSchedule.length; j++) {
      let endDate = new Date(dataIdSchedule[j].endDate)
      endDate.setDate(endDate.getDate())
      if (endDate.getTime() > today.getTime()) {
        if (dataIdSchedule[j].vaccinationPlan.id === parseInt(valueVaccPlan)) {
          if (
            dataIdSchedule[j].vaccinationCenter.id == parseInt(valueVcenter)
          ) {
            m = dataIdSchedule[j].id
          }
        }
      }
    }

    if (m !== undefined) {
      const p = m.toString()
      setSelectedSchulde(p)
    }
  }

  //me quiero traer las horas NO disponibles para ese dia seleccionado
  const [allHoursNotAvaible, setAllHoursNotAvaible] = useState(null)

  async function getHoursNotAvaibleForAday(valueDay, selectedSchulde) {
    const urlH = url + '/VacunasUYG16-api/api/Reservation/getunavailableTime'
    const responseH = await fetch(
      urlH + '/' + valueDay + '/' + selectedSchulde,
      {
        mode: 'cors',
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        cache: 'default',
      },
    )
    const dataH = await responseH.json()

    console.log('hoursNotAvaible: ' + dataH)

    setAllHoursNotAvaible(dataH)

    dataH.map((h) => ({ date: h.date }))
  }

  //// me traigo hora de inicio y fin

  const [hStart, SetHStart] = useState('')
  const [hEnd, SetHEnd] = useState('')
  const [hours, setHours] = useState([])
  const [minutes, setMinutes] = useState([])

  async function getHoursStart(selectedSchulde) {
    const urlH = url + '/VacunasUYG16-api/api/schedule/listScheduleById'
    const responseHs = await fetch(urlH + '/' + selectedSchulde, {
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })
    const dataHs = await responseHs.json()
    let hS = dataHs.startTimeDaily
    let hE = dataHs.endTimeDaily
    SetHStart(hS)
    SetHEnd(hE)

    if (hE !== '' || hS !== '') {
      const numHS = Number(hS.substring(0, 2))
      const numHE = Number(hE.substring(0, 2))
      var hours = []
      for (var i = numHS; i <= numHE; i++) {
        //CONTROL DE QUE SI LA HORA ESTÁ DISPONIBLE
        let strDrpDwnHour = ''
        if (i < 10) {
          strDrpDwnHour = '0' + i.toString()
        } else {
          strDrpDwnHour = i.toString()
        }
        hours = [...hours, { id: strDrpDwnHour, hour: strDrpDwnHour }]
      }

      setHours(hours)
    }
  }

  function getFractionForSelectedHour(strHour) {
    var arrMinutes = []
    let out = false
    let checkMin = 0
    let flgDisable = false
    while (out === false) {
      flgDisable = false
      checkMin += fractionSchedule
      //CONTROLO QUE LA FRACCIÓN ESTÉ DISPONIBLE
      if (checkMin <= 60) {
        if (checkMin !== 60) {
          flgDisable = checkHour(strHour, checkMin.toString())
          arrMinutes = [
            ...arrMinutes,
            { id: checkMin, minute: checkMin, disable: flgDisable },
          ]
        } else {
          flgDisable = checkHour(strHour, '00')
          arrMinutes = [
            { id: '00', minute: '00', disable: flgDisable },
            ...arrMinutes,
          ]
        }
      } else {
        setMinutes(arrMinutes)
        out = true
      }
    }
  }

  function checkHour(strHour, strMinute) {
    //Chequeo fracciones disponibles para esta hora seleccionada
    const dataH = allHoursNotAvaible

    for (let j = 0; j < allHoursNotAvaible.length; j++) {
      let a = dataH[j].date.split(':')
      //a[0] tiene la info de las horas
      //a[1] tiene la info de los minutos
      if (strHour === a[0]) {
        if (strMinute === a[1]) {
          return true
        }
      }
    }

    return false
  }

  //// parseo la fecha rara que me da el date picker////
  function formatDate(date) {
    var d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear()

    if (month.length < 2) month = '0' + month
    if (day.length < 2) day = '0' + day

    const x = [year, month, day].join('-')
    return x
  }

  function resrvation() {
    const axios = require('axios').default

    let strMinuteSelected = ''
    let strHourSelected = ''

    strHourSelected = hourSelected.toString()

    strMinuteSelected = minuteSelected.toString()

    const strTime = strHourSelected + ':' + strMinuteSelected

    const reserv = {
      vaccinationCenter: valueVaccCenter,
      schedule: selectedSchulde,
      citizen: ci,
      date: valueDaySelect,
      time: strTime,
      reservationState: 0,
    }

    console.log("RESERV")
    console.log(reserv)

    const sendPostRequest = async () => {
      try {
        const resp = await axios.post(
          url + '/VacunasUYG16-api/api/Reservation/createReservation2',
          reserv,
        )
        // console.log(resp.data)
      } catch (err) {
        // Handle Error Here
        // console.error(err)
      }
    }

    sendPostRequest()
  }

  function checkPreviousReservation(vaccinationPlanId) {
    //mark

    for (let i = 0; i < reservationData.length; i++) {
      if (
        reservationData[i].vaccinationPlanId.toString() === vaccinationPlanId &&
        reservationData[i].reservationState !== 'canceled'
      ) {
        return false
      }
    }

    return true
  }

  async function checkRestriction(vacPlanId) {
    //Primero obtengo lista de restricciones del plan
    const urlR =
      url +
      '/VacunasUYG16-api/api/restriction/getRestrictionsByPlan/' +
      vacPlanId

    const responseR = await fetch(urlR, {
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const dataR = await responseR.json()

    let restrictionsElementName = []

    for (let a = 0; a < dataR.length; a++) {
      restrictionsElementName = [
        ...restrictionsElementName,
        {
          elementName: dataR[a].elementName,
          description: dataR[a].description,
        },
      ]
    }

    //Luego consulto si la persona (CI), está habilitado para el plan
    const urlF =
      url +
      '/VacunasUYG16-api/api/restriction/ageRestriction/' +
      ci +
      '/' +
      vacPlanId

    const responseF = await fetch(urlF, {
      mode: 'cors',
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const dataF = await responseF.json()

    let flgChecked = true

    if (restrictionsElementName.length > 0) {
      for (let a = 0; a < restrictionsElementName.length; a++) {
        if (dataF[restrictionsElementName[a].elementName] !== undefined) {
          if (dataF[restrictionsElementName[a].elementName] === 'false') {
            setArrayRestrictionsChecked(...arrayRestrictionsChecked, {
              // prueba = [...prueba, {
              elementName: restrictionsElementName[a].elementName,
              description: restrictionsElementName[a].description,
              checked: false,
              // }]
            })
            flgChecked = false
          } else {
            setArrayRestrictionsChecked(...arrayRestrictionsChecked, {
              // prueba = [...prueba, {
              elementName: restrictionsElementName[a].elementName,
              description: restrictionsElementName[a].description,
              checked: true,
              // }]
            })
          }
        }
      }
    }

    if (flgChecked) {
      return true
    } else {
      return false
    }
  }

  return (
    <>
      <div>
        <div className=" divide-y-4 divide-black divide-opacity-25">
          <div className="text-3xl font-extrabold text-left ml-3 mb-3">
            <span className="relative py-10  bg-clip-text text-transparent bg-gradient-to-r from-blue-500 to to-denim-400">
              Nueva reserva{' '}
            </span>
          </div>
          <div className="max-w-md mx-auto ">
            <div>
              <form className="w-full max-w-lg">
                <div class="flex flex-wrap -mx-3 mb-6 mt-8">
                  <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                      className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                      htmlFor="id"
                    >
                      Cedula de identidad
                    </label>
                    <input
                      className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                      id="id"
                      name="id"
                      type="number"
                      value={ci}
                      readOnly
                    />
                  </div>
                  {/*<div className="w-full md:w-1/2 px-2">*/}
                  <div className={checkPlan ? 'px-2' : 'w-full md:w-1/2 px-2'}>
                    <label
                      className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                      htmlFor="planVacunacion"
                    >
                      Plan de Vacunación
                    </label>
                    <div className="">
                      <Dropdown
                        allData={_vacPlanNames}
                        id="id"
                        label="name"
                        className="relat"
                        onChange={async (value) => {
                          //value = vaccinationPlanId
                          if (checkPreviousReservation(value)) {
                            setValueVaccPlan(value)
                            if (await checkRestriction(value)) {
                              getVaccinationCenterForSelectedPlan(value)
                              getDaysForVaccPlan(value)
                              setCheckPlan(true)
                            } else {
                              setCheckPlan(false)
                              console.log(arrayRestrictionsChecked)
                              swal(
                                'Error',
                                'Usted no puede seleccionar este plan de vacunación ya que el mismo cuenta con restricciones que aplican a usted.',
                                'error',
                                { button: 'Entendido' },
                              )
                            }
                          } else {
                            swal(
                              'Error',
                              'Usted ya cuenta con una inscripción activa para este plan de vacunación',
                              'error',
                              { button: 'Entendido' },
                            )
                          }
                          setArrayRestrictionsChecked([])
                        }}
                      />
                      {checkPlan ? (
                        <FcOk
                          size="2em"
                          title="Usted está habilitado para este plan de vacunación"
                          className="absolute ml-52 -mt-9"
                        />
                      ) : (
                        <></>
                      )}
                    </div>
                  </div>
                </div>

                <div className="flex flex-wrap -mx-3 mb-6">
                  <div className="w-full px-3">
                    <label
                      className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                      htmlFor="vaccinationCenter"
                    >
                      Vacunatorio
                    </label>
                    <Dropdown
                      allData={_vaccinationCenterAvaible}
                      id="id"
                      label="name"
                      onChange={(valueVcenter) => {
                        console.log(valueVcenter)
                        setValueVaccCenter(valueVcenter)
                        getIdSchedule(valueVcenter)
                      }}
                    />
                  </div>
                </div>

                <div className="flex flex-wrap -mx-3 mb-6 mt-8">
                  <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                      className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                      htmlFor="id"
                    >
                      Dia
                    </label>
                    <Flatpickr
                      id="flatPickr"
                      className=" md:font sm:appearance-none block w-full text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                      //value={dateDay}
                      selected={dateDay}
                      options={{
                        dateFormat: 'Y-m-d',
                        minDate: 'today',
                        altInput: true,
                        maxDate: _daysEndSchulde,
                      }}
                      onChange={(dateDay) => {
                        setStateDay({ dateDay })
                        //
                        const y = formatDate(dateDay)
                        getFraction(selectedSchulde)
                        setvalueDaySelect(y)
                        getHoursNotAvaibleForAday(y, selectedSchulde)
                        hoursForSelect()
                      }}
                    />
                  </div>
                  <div className="w-full md:w-1/2 px-2 flex-col">
                    <div>
                      <label
                        className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                        htmlFor="planVacunacion"
                      >
                        Horario
                      </label>
                    </div>
                    <div className="flex">
                      {/*DOS DROPDOWN UNO PARA HORAS Y OTRO PARA MINUTOS*/}
                      <Dropdown
                        allData={hours}
                        id="id"
                        label="hour"
                        onChange={(selHour) => {
                          setHourSelected(selHour)
                          getFractionForSelectedHour(selHour)
                        }}
                        labelFirstOption="Hora"
                        customClass="block py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                      />
                      &nbsp;&nbsp;<label className="mt-1">:</label>&nbsp;&nbsp;
                      <Dropdown
                        allData={minutes}
                        id="id"
                        label="minute"
                        onChange={(selMinute) => {
                          setMinuteSelected(selMinute)
                        }}
                        labelFirstOption="Minutos"
                        customClass="block py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                      />
                    </div>
                  </div>
                </div>

                <div className="flex flex-wrap -mx-3 mb-2">
                  <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                    <div className="md:w-2/3">
                      <button
                        className="disabled:opacity-50 bg-denim-400 text-white active:bg-pink-600 font-bold uppercase text-sm px-4 py-3 rounded shadow hover:bg-blue-700 outline-none focus:outline-none mr-1 mt-6 ease-linear transition-all duration-150"
                        type="button"
                        disabled={
                          !valueVaccPlan ||
                          !valueVaccCenter ||
                          !dateDay ||
                          !hourSelected ||
                          !minuteSelected
                        }
                        onClick={() =>
                          Swal.fire({
                            title: '¿Desea confirmar la agenda?',
                            showDenyButton: false,
                            showCancelButton: true,
                            confirmButtonText: `Sí, confirmar`,
                            confirmButtonColor: '#27ae60 ',
                            cancelButtonColor: 'red',
                          }).then((result) => {
                            /* Read more about isConfirmed, isDenied below */
                            if (result.isConfirmed) {
                              resrvation()
                              Swal.fire(
                                'Confirmado',
                                'Se ha ingresado su solicitud de reserva',
                                'success',
                              ).then((value) => {
                                setCloseModal()
                              })
                            }
                          })
                        }
                      >
                        Confirmar
                      </button>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}
