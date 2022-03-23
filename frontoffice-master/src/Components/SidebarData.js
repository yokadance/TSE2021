// import React from 'react'
// import {AiOutlineSchedule, BiCalendar, MdTrackChanges} from "react-icons/all";
//
// export const SidebarData = ({ role }) =>  [
//
//     {
//         title: 'Agendas',
//         path: '/schedule',
//         icon: <BiCalendar size="1.5em"/>,
//     },
//     {
//         title: 'Monitor',
//         path: '/monitor',
//         icon: <MdTrackChanges size="1.5em"/>,
//     },
//     {
//         title: 'Reservas',
//         path: '/reservation',
//         icon: <AiOutlineSchedule size="1.5em"/>,
//     },
// ]
//
// export default SidebarData

import React from 'react'
import { AiOutlineSchedule, BiCalendar, MdTrackChanges } from 'react-icons/all'

export const SidebarData = ({ role }) => {
  const noRole = [
    {
      title: 'Agendas',
      path: '/schedule',
      icon: <BiCalendar size="1.5em" />,
    },
    {
      title: 'Monitor',
      path: '/monitor',
      icon: <MdTrackChanges size="1.5em" />,
    },
  ]

  const citizen = [
    ...noRole,
    {
      title: 'Reservas',
      path: '/reservation',
      icon: <AiOutlineSchedule size="1.5em" />,
    },
  ]

  const vaccinator = [
    {
      title: 'Agenda',
      path: '/vaccinatorschedule',
      icon: <BiCalendar size="1.5em" />,
    },
  ]

  const lowerCaseRole = role.toLowerCase()

  switch (lowerCaseRole) {
    case 'citizen':
      return citizen

    case 'vaccinator':
      return vaccinator

    default:
      return noRole
  }
}

export default SidebarData
