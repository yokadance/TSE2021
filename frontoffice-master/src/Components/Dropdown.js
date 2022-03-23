// Props:
//  allData: array de datos a cargar en el dropdown
//  id: campo del array de donde se van a tomar los id (value)
//  label: campo del array donde se van a tomar los label a mostrar
//  onChange: se carga el retorno, desde donde se llama se tiene que setear.
//      ej: <Dropdown
//           allData={arrayData}
//           id="code"
//           label="label"
//           onChange={(value) => setValue(value)}
//         />

import React from 'react'

const Dropdown = ({
  allData,
  id,
  label,
  onChange,
  labelFirstOption,
  noFirstOption,
  customClass,
    selectedId,
}) => {
  function selectOption(data) {
    onChange(data)
  }

  const disable = 'disable'

  return (
    <div>
      <select
        className={
          customClass === undefined
            ? 'mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm'
            : customClass
        }
        onChange={(e) => selectOption(e.target.value)}
      >
        {noFirstOption === undefined ? (
          <option disabled selected className="text-grey-300">
            {labelFirstOption !== undefined
              ? labelFirstOption
              : 'Seleccione una opción'}
          </option>
        ) : (
          <></>
        )}

        {allData &&
          allData.map((e) => {
            return (
              <>
                {e[disable] === true
                    ? (
                        <option key={e[id]} value={e[id]} disabled>{' '}
                            {e[label]}{' '}
                        </option>
                    ) : (
                        (e[id] !== selectedId ? (
                          <option key={e[id]} value={e[id]}>
                            {' '}
                            {e[label]}{' '}
                          </option>
                        ):(
                        <option key={e[id]} value={e[id]} selected>
                            {' '}
                            {e[label]}{' '}
                        </option>
                        ))
                    )
                }
              </>
            )
          })}
      </select>
    </div>
  )
}

export default Dropdown
