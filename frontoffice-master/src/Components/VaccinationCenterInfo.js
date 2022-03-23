import React, { useEffect, useState } from 'react'

const VaccinationCenterInfo = ({ vaccinationCenterId }) => {
  const [vaccinationCenterName, setVaccinationCenterName] = useState(undefined)
  const [vaccinationCenterLocation, setVaccinationCenterLocation] = useState(
    undefined,
  )

  async function getVaccCentInfo() {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR =
      url +
      '/VacunasUYG16-api/api/vaccionationCenter/listVaccinationCenterById/' +
      vaccinationCenterId

    const responseR = await fetch(urlR, {
      // crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const vaccinationCenterInfo = await responseR.json()

    setVaccinationCenterName(vaccinationCenterInfo.name)
    setVaccinationCenterLocation(vaccinationCenterInfo.location)
  }

  useEffect(async () => {
    await getVaccCentInfo()
  }, [])

  return (
    <div>
      <div className="text-3xl font-extrabold text-left ml-3 mb-3">
        <span className="relative py-10  bg-clip-text text-transparent bg-gradient-to-r from-blue-500 to to-denim-400">
          Vacunatorio{' '}
        </span>
      </div>
      <br />
      <div className="flex flex-row">
        <div className="w-1/2">
          <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
            Nombre
          </label>
          <input
            className=" md:font sm:appearance-none block w-52 bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
            value={vaccinationCenterName}
            readOnly
          />
        </div>

        <div className="w-1/2">
          <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
            Ubicación
          </label>
          <input
            className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
            value={vaccinationCenterLocation}
            readOnly
          />
        </div>
      </div>
    </div>
  )
}

export default VaccinationCenterInfo
