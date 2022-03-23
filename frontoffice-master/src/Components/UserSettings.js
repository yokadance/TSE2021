import React, { useEffect, useState } from 'react'
import Dropdown from './Dropdown'
import Flatpickr from 'react-flatpickr'
import Swal from 'sweetalert2'
import { useCookies } from 'react-cookie'
import { BlockLoading } from 'react-loadingg'

const UserSettings = ({ onChange, messageTitle }) => {
  const [loading, setLoading] = useState(true)
  const [cookies, setCookie] = useCookies(['__FOsession'])
  const [ci, setCi] = useState('')
  const [firstName, setFirstName] = useState('')
  const [secondName, setSecondName] = useState('')
  const [firstSurname, setFirstSurname] = useState('')
  const [secondSurname, setSecondSurname] = useState('')
  const [birthday, setBirthday] = useState(undefined)
  const [sex, setSex] = useState(undefined)
  const [sexId, setSexId] = useState(undefined)
  const [email, setEmail] = useState(undefined)
  const [userData, setUserData] = useState(undefined)
  const [changeEmail, setChangeEmail] = useState(undefined)

  function setCloseModal() {
    onChange(false)
  }

  useEffect(() => {
    async function getUserData(ci) {
      const url = 'https://vacunasuyg16.web.elasticloud.uy'
      // const url = 'http://localhost:8080'

      const urlR = url + '/VacunasUYG16-api/api/person/getPersonByCi/' + ci
      const responseR = await fetch(urlR, {
        mode: 'cors',
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        cache: 'default',
      })

      const userData = await responseR.json()

      // const bDate = new Intl.DateTimeFormat("es-ES").format(new Date(userData.birthday))
      // setBirthday(bDate)

      setBirthday(userData.birthday)

      setSex(userData.sex)
      setSexId(getSexId(userData.sex))
      setEmail(userData.email)
    }

    function getSexId(sexString) {
      switch (sexString) {
        case 'feminine':
          return '0'

        case 'masculine':
          return '1'

        case 'other':
          return '2'
      }
    }

    async function loadData() {
      if (cookies.__FOsession !== undefined) {
        setCi(cookies.__FOsession.ci.toString())
        setFirstName(cookies.__FOsession.firstName.toString())
        setSecondName(cookies.__FOsession.secondName.toString())
        setFirstSurname(cookies.__FOsession.firstSurname.toString())
        setSecondSurname(cookies.__FOsession.secondSurname.toString())
        await getUserData(cookies.__FOsession.ci.toString())
      }
    }

    loadData()
    setLoading(false)
  }, [])

  function getSexString(sex) {
    switch (sex) {
      case '0':
        return 'feminine'

      case '1':
        return 'masculine'

      case '2':
        return 'other'

      default:
        return sex
    }
  }

  const sexDropdownData = [
    { id: '0', name: 'Femenino' },
    { id: '1', name: 'Masculino' },
    { id: '2', name: 'Otro' },
  ]

  async function updateUserData() {
    const axios = require('axios').default

    const bDate = new Intl.DateTimeFormat('en-EN', {
      year: 'numeric',
      day: '2-digit',
      month: '2-digit',
    }).format(new Date(birthday))

    //EJ: "birthday": "1988-10-15T00:00:01.000Z",
    const strDate =
      bDate.substr(6, 4) +
      '-' +
      bDate.substr(0, 2) +
      '-' +
      bDate.substr(3, 2) +
      'T00:00:00.000Z'

    const message = {
      ci: ci,
      sex: getSexString(sex),
      birthday: strDate,
      email: email,
    }

    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const sendPostRequest = async () => {
      try {
        const resp = await axios.post(
          url + '/VacunasUYG16-api/api/person/savePerson',
          message,
        )
        return true
      } catch (err) {
        // Handle Error Here
        return false
      }
    }

    return await sendPostRequest()
  }

  return (
    <>
      <div className="divide-y-4 divide-black divide-opacity-25">
        <div className="text-3xl font-extrabold text-left ml-3 mb-3">
          <span className="relative py-10 bg-clip-text text-transparent bg-gradient-to-r from-blue-500 to to-denim-400">
            {messageTitle === undefined ? 'DATOS DEL USUARIO' : messageTitle}
          </span>
        </div>
        {/*{loading ? (*/}
        {/*  <div className="w-full">*/}
        {/*    <div className="grid justify-items-center mt-10">*/}
        {/*      /!*<CircleLoading style={{ }} color='#75AFD6' size='large'/>*!/*/}
        {/*      <BlockLoading style={{}} color="#75AFD6" size="large" />*/}
        {/*      /!*<LoopCircleLoading style={{ }} color='#75AFD6' size='large'/>*!/*/}
        {/*    </div>*/}
        {/*  </div>*/}
        {/*) : (*/}
        <div className="max-w-md mx-auto ">
          <div>
            <form className="w-full max-w-lg">
              <div className="flex flex-wrap -mx-3 mb-6 mt-8">
                <div className="md:w-1/3 px-3 mb-6 md:mb-0">
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
                <div className="md:w-1/3 px-2">
                  <label
                    className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    htmlFor="planVacunacion"
                  >
                    Fecha de Nacimiento
                  </label>
                  <Flatpickr
                    id="flatPickr"
                    className=" md:font sm:appearance-none block w-full text-gray-700 border Zrounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                    value={birthday}
                    selected={birthday}
                    options={{
                      dateFormat: 'Y-m-d',
                      altInput: true,
                    }}
                    onChange={(dateDay) => {
                      setBirthday(dateDay)
                    }}
                  />
                </div>
                <div className="md:w-1/3 px-2 mt-4">
                  <label
                    className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    htmlFor=""
                  >
                    Sexo
                  </label>
                  <Dropdown
                    allData={sexDropdownData}
                    id="id"
                    label="name"
                    className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none focus:bg-transparent"
                    noFirstOption={true}
                    selectedId={sexId}
                    onChange={(value) => {
                      setSex(value)
                    }}
                  />
                </div>
              </div>

              <div className="flex flex-wrap -mx-3 mb-6 mt-8">
                <div className="md:w-1/2 px-3">
                  <label
                    className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    htmlFor="vaccinationCenter"
                  >
                    Primer nombre
                  </label>
                  <input
                    className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                    id="id"
                    name="id"
                    type="text"
                    value={firstName}
                    readOnly
                  />
                </div>

                <div className="md:w-1/2 px-3">
                  <label
                    className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    htmlFor="vaccinationCenter"
                  >
                    Segundo nombre
                  </label>
                  <input
                    className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                    id="id"
                    name="id"
                    type="text"
                    value={secondName}
                    readOnly
                  />
                </div>
              </div>

              <div className="flex flex-wrap -mx-3 mb-6 mt-8">
                <div className="md:w-1/2 px-3">
                  <label
                    className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    htmlFor="vaccinationCenter"
                  >
                    Primer apellido
                  </label>
                  <input
                    className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                    id="id"
                    name="id"
                    type="text"
                    value={firstSurname}
                    readOnly
                  />
                </div>

                <div className="md:w-1/2 px-3">
                  <label
                    className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    htmlFor="vaccinationCenter"
                  >
                    Segundo apellido
                  </label>
                  <input
                    className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                    id="id"
                    name="id"
                    type="text"
                    value={secondSurname}
                    readOnly
                  />
                </div>
              </div>

              <div className="flex flex-wrap -mx-3 mb-6">
                <div className="w-full px-3">
                  <label
                    className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    htmlFor=""
                  >
                    Email
                  </label>
                  <input
                    className=" md:font sm:appearance-none block w-full  text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
                    id="id"
                    name="id"
                    type="text"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
              </div>

              <br />

              <div className="absolute -mx-3 mb-3 bottom-0 right-6">
                <div className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                  <div className="md:w-2/3">
                    <button
                      className="disabled:opacity-50 bg-denim-400 text-white active:bg-pink-600 font-bold uppercase text-sm px-4 py-3 rounded shadow hover:bg-blue-700 outline-none focus:outline-none mr-1 mt-6 ease-linear transition-all duration-150"
                      type="button"
                      disabled={!email || !birthday}
                      onClick={() =>
                        Swal.fire({
                          title: '¿Desea confirmar los cambios?',
                          showDenyButton: false,
                          showCancelButton: true,
                          confirmButtonText: `Sí, confirmar`,
                          confirmButtonColor: '#27ae60 ',
                          cancelButtonColor: 'red',
                        }).then(async (result) => {
                          /* Read more about isConfirmed, isDenied below */
                          if (result.isConfirmed) {
                            if (await updateUserData()) {
                              Swal.fire(
                                'Confirmado',
                                'Sus datos han sido satisfactoriamente actualizados',
                                'success',
                              ).then((value) => {
                                setCloseModal()
                              })
                            } else {
                              Swal.fire(
                                'Error',
                                'Ha sucedido un error al actualizar sus datos, por favor intente más tarde',
                                'error',
                              ).then((value) => {
                                // window.location = '/reservation'
                              })
                            }
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
        {/*)}*/}
      </div>
    </>
  )
}

export default UserSettings
