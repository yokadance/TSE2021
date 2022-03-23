// Props:
//  title: Título a mostrar
//  strStyle: Estilo que quiera agregársele al que tiene de base
//      ej: <Title title="Reservas" strStyle="ml-10"/>

import React from 'react'

const Title = ({ title, strStyle }) => {
  const titleStyle =
    'text-5xl font-bold text-denim-555 font-normal leading-normal mt-0 mb-2 ' +
    strStyle

  return (
    <div>
      <h1 className={titleStyle}>{title}</h1>
      {<br />}
    </div>
  )
}

export default Title
