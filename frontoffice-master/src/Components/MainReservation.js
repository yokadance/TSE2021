import React, { useEffect, useState } from 'react'
import Table from './react-tailwind-table' //https://www.npmjs.com/package/react-tailwind-table
import { FcCancel } from 'react-icons/all'
import Title from './Title'
import _Modal from './_Modal'
import { Cookies } from 'react-cookie'
import Swal from 'sweetalert2'
import {
  FacebookIcon,
  FacebookShareButton,
  TelegramIcon,
  TelegramShareButton,
  TwitterIcon,
  TwitterShareButton,
  WhatsappIcon,
  WhatsappShareButton,
} from 'react-share'
import { BlockLoading } from 'react-loadingg'
import swal from '@sweetalert/with-react'
import { Link } from 'react-router-dom'

const MainReservation = () => {
  const [loading, setLoading] = useState(true)
  const url = 'https://vacunasuyg16.web.elasticloud.uy'
  // const url = 'http://localhost:8080'
  const [ci, setCi] = useState('')
  const [showModal, setShowModal] = useState(false)
  const [showShareModal, setShowShareModal] = useState(false)
  const [reservationData, set_reservationData] = useState([
    {
      reservationId: '',
      vaccinationPlanName: '',
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

  const c = new Cookies()

  const getCook = () => {
    if (c.get('__FOsession') !== undefined) {
      return c.get('__FOsession')
    }
  }

  const cook = getCook()

  // const cook = c.get('__FOsession')

  useEffect(() => {
    if (c.get('__FOsession') !== undefined) {
      const cook = getCook() //c.get('__FOsession')

      if (cook !== undefined) {
        if (cook.role.toString() !== 'Citizen') {
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

    getReservationList()
  }, [])

  async function getReservationList() {
    const cookie = new Cookies()
    let ci_aux = ''
    const a = cookie.get('__FOsession')
    if (a !== undefined) {
      // setUser(a.userName.toString())
      // setRol(a.role.toString())
      ci_aux = a.ci
      setCi(a.ci)
    }

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

    setLoading(false)
  }

  const reservationColumnGridName = [
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
      field: 'vaccinationPlanName',
      use: 'Plan de vacunación',
    },
    {
      field: 'reservationCenterName',
      use: 'Vacunatorio',
    },
    {
      field: 'vaccinationPostName',
      use: 'Puesto de vacunación',
    },
    {
      field: 'doses',
      use: 'Dosis',
    },
    {
      field: 'reservationDate',
      use: 'Fecha',
    },
    {
      field: 'reservationTime',
      use: 'Turno',
    },
    {
      field: 'reservationState',
      use: 'Estado',

      //Will not be displayed in the table
      //use_in_display: false
    },
    {
      field: 'buttons',
      use: '',

      //Will not be displayed in the table
      //use_in_display: false
    },
  ]

  const rowcheck = (row, column, display_value) => {
    if (column.field === 'buttons') {
      // 0: Pendiente
      // 1: Hecho
      // 2: Cancelado

      if (row.reservationState === 'pending') {
        return (
          <FcCancel
            cursor="pointer"
            size="2em"
            title="Cancelar reserva"
            onClick={() =>
              Swal.fire({
                title: '¿Está seguro que desea cancelar la reserva?',
                showDenyButton: false,
                showCancelButton: true,
                confirmButtonText: `Sí, cancelar`,
                confirmButtonColor: '#27ae60 ',
                cancelButtonText: `No`,
                cancelButtonColor: 'red',
              }).then(async (result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {
                  if (await cancelReservation(row.reservationId)) {
                    Swal.fire(
                      'Confirmado',
                      'Se ha cancelado la reserva',
                      'success',
                    ).then((value) => {
                      getReservationList()
                    })
                  } else {
                    Swal.fire(
                      'Error',
                      'Ha sucedido un error al cancelar su reserva',
                      'error',
                    ).then((value) => {
                      // window.location = '/reservation'
                    })
                  }
                }
              })
            }
          />
        )
      }

      if (row.reservationState === 'confirmed') {
        // return <button className="border p-2">Hecho</button>
        const shareUrl = 'https://node1340-vacunasuyg16.web.elasticloud.uy/'
        const title =
          '¡ME HE VACUNADO! ¡Coordiné mi vacunación utilizando la app vacunas.uy! Ingresá tu también y vacunate...'
        const twitterShareMsg =
          '¡ME HE VACUNADO! ¡Coordiné mi vacunación utilizando la app vacunas.uy! Ingresá tu también y vacunate... https://node1340-vacunasuyg16.web.elasticloud.uy/'
        return (
          <>
            <FacebookShareButton
              url={shareUrl}
              quote={title}
              windowWidth={500}
              windowHeight={500}
            >
              <FacebookIcon size={32} round />
            </FacebookShareButton>
            <TwitterShareButton
              url={twitterShareMsg}
              windowWidth={500}
              windowHeight={500}
            >
              <TwitterIcon size={32} round />
            </TwitterShareButton>
          </>
        )
      }

      if (
        row.reservationState === 'canceled' ||
        row.reservationState === 'rejected'
      ) {
        //return <button className="border p-2">Cancelado</button>
        // return <FcCancel cursor= "pointer" size="2em" title="ASD"/>
      }
    }

    if (column.field === 'reservationState') {
      if (row.reservationState === 'pending') {
        return 'Pendiente'
      }

      if (row.reservationState === 'confirmed') {
        return 'Confirmado'
      }

      if (row.reservationState === 'canceled') {
        return 'Cancelado'
      }

      if (row.reservationState === 'rejected') {
        return 'Rechazado'
      }
    }

    if (column.field === 'doses') {
      return "Dosis " + row.doses
    }

    return display_value
  }

  async function cancelReservation(reservationId) {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR =
      url +
      '/VacunasUYG16-api/api/Reservation/cancelReservation/' +
      reservationId
    const responseR = await fetch(urlR, {
      // crossDomain: true,
      mode: 'cors',
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    let boolResult = false

    const jsonRet = await responseR.json()

    boolResult = jsonRet.ok

    return boolResult
  }

  return (
    <>
      <div className="grid justify-items-center">
        <Title title="Reservas" />
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
        <div className="">
          {/*Title*/}
          <div className="">
            <button
              // className="absolute mt-8 ml-7 bg-denim-555 text-white font-bold text-sm px-6 py-2 rounded "
              className="transition duration-300 ease-in-out transform hover:scale-105 absolute mt-8 ml-7 bg-denim-555 text-white font-bold text-sm px-6 py-2 rounded "
              type="button"
              onClick={() => setShowModal(true)}
            >
              Nueva reserva
            </button>
            {showModal ? (
              <>
                <_Modal
                  Type={'agendarse'}
                  onClick={(value) => {
                    setShowModal(value)
                    getReservationList()
                  }}
                />
              </>
            ) : null}
            {showShareModal ? (
              <>
                <_Modal
                  Type={'share'}
                  onClick={(value) => setShowShareModal(value)}
                />
              </>
            ) : null}
          </div>

          {/*Para el grid*/}
          <div>
            <Table
              columns={reservationColumnGridName}
              rows={reservationData}
              per_page="5"
              no_content_text="No hay resultados para mostrar..."
              row_render={rowcheck}
            />
          </div>
        </div>
      )}
    </>
  )
}

export default MainReservation
