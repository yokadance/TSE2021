import React, { useEffect, useState } from 'react'

const VaccinationPlanInfo = ({ vaccinationPlanId }) => {
  const [vaccinationPlanName, setVaccinationPlanName] = useState(undefined)
  const [vaccinationPlanStartDate, setVaccinationPlanStartDate] = useState(
    undefined,
  )
  const [vaccinationPlanEndDate, setVaccinationPlanEndDate] = useState(
    undefined,
  )

  async function getVaccPlanInfo() {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR =
      url +
      '/VacunasUYG16-api/api/vaccionationPlan/listVaccinationPlanById/' +
      vaccinationPlanId

    const responseR = await fetch(urlR, {
      // crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const vaccinationPlanInfo = await responseR.json()

    setVaccinationPlanName(vaccinationPlanInfo.name)
    setVaccinationPlanStartDate(vaccinationPlanInfo.startDate)
    setVaccinationPlanEndDate(vaccinationPlanInfo.endDate)
  }

  useEffect(async () => {
    await getVaccPlanInfo()
  }, [])

  return (
    <div>
      <div className="text-3xl font-extrabold text-left ml-3 mb-3">
        <span className="relative py-10  bg-clip-text text-transparent bg-gradient-to-r from-blue-500 to to-denim-400">
          Plan de vacunación{' '}
        </span>
      </div>
      <br />
      <div className="flex flex-col">
        <div>
          <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
            Nombre
          </label>
          <input
            className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
            value={vaccinationPlanName}
            readOnly
          />
        </div>

        <div className="flex flex-row">
          <div className="w-1/2">
            <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
              Fecha de inicio
            </label>
            <input
              className=" md:font sm:appearance-none block w-52 bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
              value={vaccinationPlanStartDate}
              readOnly
            />
          </div>

          <div className="w-1/2">
            <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
              Fecha de fin
            </label>
            <input
              className=" md:font sm:appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-2 px-5 mb-3 leading-tight focus:outline-none "
              value={vaccinationPlanEndDate}
              readOnly
            />
          </div>
        </div>
      </div>
    </div>
  )
}

export default VaccinationPlanInfo
