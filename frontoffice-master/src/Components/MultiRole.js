import React from 'react'
import { Link } from 'react-router-dom'

const MultiRole = ({ onClick }) => {
  function setRole(role) {
    onClick(role)
  }

  return (
    <>
      <h1 className="ml-6 mt-10">
        Por favor, seleccione con qué rol desea ingresar al sistema
      </h1>
      <div className="h-28 grid grid-cols-2 ml-7 mt-6">
        {/*//BUTTON 1 - CIUDADANO*/}
        <div className="ml-1">
          <Link to="/">
            <button
              // className="absolute mt-8 ml-7 bg-denim-555 text-white font-bold text-sm px-6 py-2 rounded "
              className="transition duration-300 ease-in-out transform hover:scale-105 absolute mt-8 ml-7 bg-denim-555 text-white font-bold text-sm px-6 py-2 rounded "
              type="button"
              onClick={() => setRole('Citizen')}
            >
              Ciudadano
            </button>
          </Link>
        </div>

        <div className="ml-1">
          {/*BUTTON2 - VACUNADOR*/}
          <Link to="/">
            <button
              // className="absolute mt-8 ml-7 bg-denim-555 text-white font-bold text-sm px-6 py-2 rounded "
              className="transition duration-300 ease-in-out transform hover:scale-105 absolute mt-8 ml-7 bg-denim-555 text-white font-bold text-sm px-6 py-2 rounded "
              type="button"
              onClick={() => setRole('Vaccinator')}
            >
              Vacunador
            </button>
          </Link>
        </div>
      </div>
    </>
  )
}

export default MultiRole
