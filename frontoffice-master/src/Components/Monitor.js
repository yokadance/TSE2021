import React, { useEffect, useState } from 'react'
import Dropdown from './Dropdown'
import Title from './Title'
import { Line, Pie } from 'react-chartjs-2'
import { BlockLoading } from 'react-loadingg'

const Monitor = () => {
  const [loading, setLoading] = useState(true)
  const [vaccinePlan, setVaccinePlan] = useState(undefined)
  const [vaccineName, setVaccineName] = useState(undefined)
  const [disease, setDisease] = useState(undefined)
  const [dataLine, setDataLine] = useState(undefined)
  const [optionsLine, setOptionsLine] = useState(undefined)
  const [monitorData, setMonitorData] = useState({
    vPlanId: '',
    vPlanName: '',
    vaccineName: '',
    diseaseName: '',
    startDate: '',
    endDate: '',
    today: '',
    totalDosesVaccine: 0,
    doseQuantity: '',
    doses1: 0,
    doses2: 0,
    doses3: 0,
    doses4: 0,
    doses5: 0,
    dates: [],
  })

  const [dataPie, setDataPie] = useState({
    labels: ['primera dosis', 'segunda dosis'],
    datasets: [
      {
        data: [monitorData.doses1, monitorData.doses2],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
          'rgb(246,192,60)',
          'rgb(66,250,250)',
          'rgb(152,106,253)',
          'rgb(255,155,76)',
        ],
        hoverOffset: 4,
      },
    ],
  })

  const url = 'https://vacunasuyg16.web.elasticloud.uy'
  // const url = 'http://localhost:8080'

  // const mockData = {
  //   vaccinationPlanId: 1,
  //   vaccinationPlanName: 'Uruguay vs COVID 19',
  //   vaccineName: 'Pfizer',
  //   diseaseName: 'COVID-19',
  //   startDate: '2021-02-27',
  //   endDate: '2021-08-31',
  //   totalDosesAsssigned: 15000,
  //   today: '6/7/2021',
  //   totalPeople1Dose: 7900,
  //   totalPeople2Dose: 4757,
  //   dateDoses: [
  //     {
  //       date: '2/27/2021',
  //       dose1: 100,
  //       dose2: 0,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '3/6/2021',
  //       dose1: 646,
  //       dose2: 0,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '3/13/2021',
  //       dose1: 1192,
  //       dose2: 0,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '3/20/2021',
  //       dose1: 1738,
  //       dose2: 0,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '3/27/2021',
  //       dose1: 2284,
  //       dose2: 0,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '4/3/2021',
  //       dose1: 2830,
  //       dose2: 402,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '4/10/2021',
  //       dose1: 3376,
  //       dose2: 871,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '4/17/2021',
  //       dose1: 3922,
  //       dose2: 1340,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '4/24/2021',
  //       dose1: 4468,
  //       dose2: 1809,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '5/1/2021',
  //       dose1: 5014,
  //       dose2: 2278,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '5/8/2021',
  //       dose1: 5560,
  //       dose2: 2747,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '5/15/2021',
  //       dose1: 6106,
  //       dose2: 3216,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '5/22/2021',
  //       dose1: 6652,
  //       dose2: 3685,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '5/29/2021',
  //       dose1: 7198,
  //       dose2: 4154,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //     {
  //       date: '6/7/2021',
  //       dose1: 7900,
  //       dose2: 4757,
  //       dose3: 0,
  //       dose4: 0,
  //       dose5: 0,
  //     },
  //   ],
  // }

  // const mockVaccPlan = [
  //   { id: 1, name: 'Uruguay vs COVID 19' },
  //   { id: 2, name: 'Plan vacunación 2' },
  //   { id: 3, name: 'Plan vacunación 3' },
  //   { id: 4, name: 'Plan vacunación 4' },
  // ]

  const [_vacPlanNames, set_vacPlanNames] = useState([
    {
      id: '',
      name: '',
    },
  ])

  // const dataPie = {
  //     labels: ['primera dosis', 'segunda dosis'],
  //     datasets: [
  //         {
  //             data: [monitorData.doses1, monitorData.doses2],
  //             backgroundColor: [
  //                 'rgb(255, 99, 132)',
  //                 'rgb(54, 162, 235)',
  //                 'rgb(246,192,60)',
  //                 'rgb(66,250,250)',
  //                 'rgb(152,106,253)',
  //                 'rgb(255,155,76)',
  //             ],
  //             hoverOffset: 4,
  //         },
  //     ],
  // }

  const optionPie = {
    maintainAspectRatio: false,
    responsive: false,
    legend: {
      position: 'left',
    },
  }

  useEffect(() => {
    // processData()
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

      console.log('firstVacPlan')

      const firstVacPlan = dataPlan[0].id

      console.log('firstVacPlan')
      console.log(firstVacPlan)

      await processData(firstVacPlan)

      return firstVacPlan
    }

    getVaccinationPlan()

    // console.log("id")
    // console.log(id)
    //
    // processData(id)
  }, [])

  async function processData(vaccinationPlanId) {
    //FETCH DATA
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR =
      url +
      '/VacunasUYG16-api/api/vaccionationPlan/getDataMonVPlan/' +
      vaccinationPlanId

    const responseR = await fetch(urlR, {
      // crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const monitorData = await responseR.json()

    //CARGO DATOS A MOSTRAR
    setMonitorData({
      vPlanId: monitorData.vPlanId,
      vPlanName: monitorData.vPlanName,
      vaccineName: monitorData.vaccineName,
      diseaseName: monitorData.diseaseName,
      startDate: monitorData.startDate,
      endDate: monitorData.endDate,
      today: monitorData.today,
      totalDosesAsssigned: monitorData.totalDosesAsssigned,
      doseQuantity: monitorData.doseQuantity,
      doses1: monitorData.doses1,
      doses2: monitorData.doses2,
      doses3: monitorData.doses3,
      doses4: monitorData.doses4,
      doses5: monitorData.doses5,
      dates: monitorData.dates,
      // vaccinationPlanId: mockData.vaccinationPlanId,
      // vaccinationPlanName: mockData.vaccinationPlanName,
      // vaccineName: mockData.vaccineName,
      // diseaseName: mockData.diseaseName,
      // startDate: mockData.startDate,
      // endDate: mockData.endDate,
      // today: mockData.today,
      // totalDosesAsssigned: mockData.totalDosesAsssigned,
      // totalPeople1Dose: mockData.totalPeople1Dose,
      // totalPeople2Dose: mockData.totalPeople2Dose,
      // dateDoses: mockData.dateDoses,
    })

    // setVaccineName(mockData.vaccineName)
    // setDisease(mockData.diseaseName)

    setVaccineName(monitorData.vaccineName)
    setDisease(monitorData.diseaseName)

    // processLineChartData()
    let dates = []
    let doses1 = []
    let doses2 = []
    let doses3 = []
    let doses4 = []
    let doses5 = []

    monitorData.dates.map(
      (d) => (
        (dates = [...dates, d.date]),
        (doses1 = [...doses1, d.doses1]),
        (doses2 = [...doses2, d.doses2]),
        (doses3 = [...doses3, d.doses3]),
        (doses4 = [...doses4, d.doses4]),
        (doses5 = [...doses5, d.doses5])
      ),
    )

    let arrayDataSets = []

    // if (monitorData.doseQuantity >= 1) {
    //   arrayDataSets = [
    //     {
    //       label: 'Cantidad de vacunados con la primer dosis',
    //       data: doses1,
    //       fill: true,
    //       backgroundColor: 'rgb(255, 99, 132)',
    //       pointBackgroundColor: 'rgb(182,50,71)',
    //     },
    //   ]
    // }
    //
    // if (monitorData.doseQuantity >= 2) {
    //   arrayDataSets = [
    //     ...arrayDataSets,
    //     {
    //       label: 'Cantidad de vacunados con la segunda dosis',
    //       data: doses2,
    //       fill: true,
    //       backgroundColor: 'rgb(54, 162, 235)',
    //       pointBackgroundColor: 'rgb(0,101,167)',
    //     },
    //   ]
    // }
    //
    // if (monitorData.doseQuantity >= 3) {
    //   arrayDataSets = [
    //     ...arrayDataSets,
    //     {
    //       label: 'Cantidad de vacunados con la tercera dosis',
    //       data: doses3,
    //       fill: true,
    //       backgroundColor: 'rgb(54,235,105)',
    //       pointBackgroundColor: 'rgb(0,167,67)',
    //     },
    //   ]
    // }
    //
    // if (monitorData.doseQuantity >= 4) {
    //   arrayDataSets = [
    //     ...arrayDataSets,
    //     {
    //       label: 'Cantidad de vacunados con la cuarta dosis',
    //       data: doses4,
    //       fill: true,
    //       backgroundColor: 'rgb(196,54,235)',
    //       pointBackgroundColor: 'rgb(117,0,167)',
    //     },
    //   ]
    // }
    //
    // if (monitorData.doseQuantity >= 5) {
    //   arrayDataSets = [
    //     ...arrayDataSets,
    //     {
    //       label: 'Cantidad de vacunados con la quinta dosis',
    //       data: doses5,
    //       fill: true,
    //       backgroundColor: 'rgb(229,235,54)',
    //       pointBackgroundColor: 'rgb(167,164,0)',
    //     },
    //   ]
    // }

    if (monitorData.doseQuantity >= 5) {
      arrayDataSets = [
        {
          label: 'Cantidad de vacunados con la quinta dosis',
          data: doses5,
          fill: true,
          backgroundColor: 'rgb(229,235,54)',
          pointBackgroundColor: 'rgb(167,164,0)',
        },
      ]
    }

    if (monitorData.doseQuantity >= 4) {
      arrayDataSets = [
        ...arrayDataSets,
        {
          label: 'Cantidad de vacunados con la cuarta dosis',
          data: doses4,
          fill: true,
          backgroundColor: 'rgb(196,54,235)',
          pointBackgroundColor: 'rgb(117,0,167)',
        },
      ]
    }

    if (monitorData.doseQuantity >= 3) {
      arrayDataSets = [
        ...arrayDataSets,
        {
          label: 'Cantidad de vacunados con la tercera dosis',
          data: doses3,
          fill: true,
          backgroundColor: 'rgb(54,235,105)',
          pointBackgroundColor: 'rgb(0,167,67)',
        },
      ]
    }

    if (monitorData.doseQuantity >= 2) {
      arrayDataSets = [
        ...arrayDataSets,
        {
          label: 'Cantidad de vacunados con la segunda dosis',
          data: doses2,
          fill: true,
          backgroundColor: 'rgb(54, 162, 235)',
          pointBackgroundColor: 'rgb(0,101,167)',
        },
      ]
    }

    if (monitorData.doseQuantity >= 1) {
      arrayDataSets = [
        ...arrayDataSets,
        {
          label: 'Cantidad de vacunados con la primer dosis',
          data: doses1,
          fill: true,
          backgroundColor: 'rgb(255, 99, 132)',
          pointBackgroundColor: 'rgb(182,50,71)',
        },
      ]
    }

    setDataLine({
      labels: dates,
      datasets: arrayDataSets,
      // [
      //     {
      //         label: 'Cantidad de vacunados con la quinta dosis',
      //         data: doses5,
      //         fill: true,
      //         backgroundColor: 'rgb(229,235,54)',
      //         pointBackgroundColor: 'rgb(167,164,0)',
      //     },
      //     {
      //         label: 'Cantidad de vacunados con la cuarta dosis',
      //         data: doses4,
      //         fill: true,
      //         backgroundColor: 'rgb(196,54,235)',
      //         pointBackgroundColor: 'rgb(117,0,167)',
      //     },
      //     {
      //         label: 'Cantidad de vacunados con la tercera dosis',
      //         data: doses3,
      //         fill: true,
      //         backgroundColor: 'rgb(54,235,105)',
      //         pointBackgroundColor: 'rgb(0,167,67)',
      //     },
      //     {
      //         label: 'Cantidad de vacunados con la segunda dosis',
      //         data: doses2,
      //         fill: true,
      //         backgroundColor: 'rgb(54, 162, 235)',
      //         pointBackgroundColor: 'rgb(0,101,167)',
      //     },
      //     {
      //         label: 'Cantidad de vacunados con la primer dosis',
      //         data: doses1,
      //         fill: true,
      //         backgroundColor: 'rgb(255, 99, 132)',
      //         pointBackgroundColor: 'rgb(182,50,71)',
      //     },
      // ],
    })

    let arrayLabelsDataPie = []
    let arrayBGDataPie = []
    let arrayDataDataPie = []

    if (monitorData.doseQuantity >= 1) {
      arrayLabelsDataPie = ['primera dosis']
      arrayBGDataPie = ['rgb(255, 99, 132)']
      arrayDataDataPie = [monitorData.doses1]
    }

    if (monitorData.doseQuantity >= 2) {
      arrayLabelsDataPie = [...arrayLabelsDataPie, 'segunda dosis']
      arrayBGDataPie = [...arrayBGDataPie, 'rgb(54, 162, 235)']
      arrayDataDataPie = [...arrayDataDataPie, monitorData.doses2]
    }

    if (monitorData.doseQuantity >= 3) {
      arrayLabelsDataPie = [...arrayLabelsDataPie, 'tercera dosis']
      arrayBGDataPie = [...arrayBGDataPie, 'rgb(54,235,105)']
      arrayDataDataPie = [...arrayDataDataPie, monitorData.doses3]
    }

    if (monitorData.doseQuantity >= 4) {
      arrayLabelsDataPie = [...arrayLabelsDataPie, 'cuarta dosis']
      arrayBGDataPie = [...arrayBGDataPie, 'rgb(196,54,235)']
      arrayDataDataPie = [...arrayDataDataPie, monitorData.doses4]
    }

    if (monitorData.doseQuantity >= 5) {
      arrayLabelsDataPie = [...arrayLabelsDataPie, 'quinta dosis']
      arrayBGDataPie = [...arrayBGDataPie, 'rgb(229,235,54']
      arrayDataDataPie = [...arrayDataDataPie, monitorData.doses5]
    }

    setDataPie({
      labels: arrayLabelsDataPie,
      //['primera dosis', 'segunda dosis'],
      datasets: [
        {
          data: arrayDataDataPie,
          // [monitorData.doses1, monitorData.doses2],
          backgroundColor: arrayBGDataPie,
          //     [
          //     'rgb(255, 99, 132)',
          //     'rgb(54, 162, 235)',
          //     'rgb(246,192,60)',
          //     'rgb(66,250,250)',
          //     'rgb(152,106,253)',
          //     'rgb(255,155,76)',
          // ],
          hoverOffset: 4,
        },
      ],
    })

    setLoading(false)
  }

  function processLineChartData() {
    // console.log("monitorData")
    // console.log(monitorData)
    // let dates = []
    // let doses1 = []
    // let doses2 = []
    // let doses3 = []
    // let doses4 = []
    // let doses5 = []
    //
    // monitorData.dates.map(
    //     (d) => (
    //         (dates = [...dates, d.date]),
    //             (doses1 = [...doses1, d.doses1]),
    //             (doses2 = [...doses2, d.doses2]),
    //             (doses3 = [...doses3, d.doses3]),
    //             (doses4 = [...doses4, d.doses4]),
    //             (doses5 = [...doses5, d.doses5])
    //     ),
    // )
    //
    // setDataLine({
    //     labels: dates,
    //     datasets: [
    //         {
    //             label: 'Cantidad de vacunados con la segunda dosis',
    //             data: doses2,
    //             fill: true,
    //             backgroundColor: 'rgb(54, 162, 235)',
    //             pointBackgroundColor: 'rgb(0,101,167)',
    //         },
    //         {
    //             label: 'Cantidad de vacunados con la primer dosis',
    //             data: doses1,
    //             fill: true,
    //             backgroundColor: 'rgb(255, 99, 132)',
    //             pointBackgroundColor: 'rgb(182,50,71)',
    //         },
    //     ],
    // })
  }

  const optionLine = {
    maintainAspectRatio: false,
  }

  return (
    <>
      <div className="grid justify-items-center">
        <Title title="Monitor de vacunación" />
      </div>
      {/*{loading ? (*/}
      {/*  <div className="w-full">*/}
      {/*    <div className="grid justify-items-center mt-20">*/}
      {/*      <BlockLoading style={{}} color="#75AFD6" size="large" />*/}
      {/*    </div>*/}
      {/*  </div>*/}
      {/*) : (*/}
      <div className=" flex flex-col ">
        {/*Filtros y datos principales (Plan de vacunación (filtro) - Vacuna - Enfermedad*/}
        <div className="flex flex-row justify-between">
          <div className="w-1/2">
            <div className="flex flex-row">
              <label className="text-xl mt-1">Plan de vacunación:</label>
              &nbsp;&nbsp;&nbsp;
              <Dropdown
                allData={_vacPlanNames}
                id="id"
                label="name"
                customClass="text-xl block py-1 px-6 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 "
                noFirstOption="true"
                onChange={async (value) => {
                  setVaccinePlan(value)
                  await processData(value)
                }}
              />
            </div>
          </div>
          <div className="w-1/3">
            <div className="ml-10 mt-1">
              <label className="text-xl">Vacuna:</label>
              &nbsp;&nbsp;
              <label className="text-xl">{vaccineName}</label>
            </div>
          </div>
          <div className="flex flex-col w-1/3 mt-1">
            <div className="flex justify-center">
              <label className="text-xl">Enfermedad:</label>
              &nbsp;&nbsp;
              <label className="text-xl">{disease}</label>
            </div>
          </div>
        </div>

        <br />
        {loading ? (
          <div className="w-full">
            <div className="grid justify-items-center mt-20">
              <BlockLoading style={{}} color="#75AFD6" size="large" />
            </div>
          </div>
        ) : (
          <div className="">
            {/*Pie chart y datos adicionales*/}
            <div className="flex flex-row justify-around">
              {/*Pie chart*/}
              <div className="w-1/2 flex justify-center">
                <br />
                <label className="mt-1">Vacunados con:</label>
                <Pie data={dataPie} options={optionPie} />
                <br />
              </div>

              {/*Datos adicionales*/}
              <div className="w-1/2 mt-5 ml-8">
                <div>
                  <label className="text-lg mt-1">
                    Datos relevantes del plan de vacunación:
                  </label>
                </div>
                <div className="ml-10 mt-3">
                  <label>- Fecha de inicio: {monitorData.startDate}</label>
                  <br />
                  <label>- Fecha de fin: {monitorData.endDate}</label>
                  <br />
                  <label>
                    - Cantidad de vacunas asignadas:{' '}
                    {monitorData.totalDosesAsssigned}
                  </label>
                  <br />
                  <label>
                    - Cantidad de dosis de la vacuna: {monitorData.doseQuantity}
                  </label>
                </div>
              </div>
            </div>
          </div>
        )}
        <br />
        <br />

        {/*Line chart*/}
        <div className="h-96 ml-6">
          <Line
            data={dataLine}
            // options={optionsLine}
            options={optionLine}
          />
        </div>
      </div>
      {/*)*/}
      {/*  }*/}
    </>
  )
}

export default Monitor
