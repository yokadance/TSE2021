import React, { useState } from 'react'

export default function Home() {
  const [login, setLogin] = useState(false)
  return (
    // <div className=" text-white py-20 ">
    <div className="container mx-auto flex flex-col md:flex-row items-center my-12 md:my-24">
      <div className="flex flex-col w-full lg:w-1/3 ">
        <h1 className="text-3xl md:text-8xl font-semibold p-0 text-denim-555 tracking-loose pb-3">
          vacunas.uy
        </h1>
        <br />
        <p className="text-sm md:text-2xl text-denim-444 mb-4 pl-4 ">
          Bienvenido al sistema de gestión de vacunas y vacunaciones
        </p>
        {/*</div>*/}
      </div>
      <div className="p-8 mt-12 mb-6 md:mb-0 md:mt-0 ml-0 md:ml-12 lg:w-2/3 object-bottom justify-center">
        {/*<div className="h-48 flex flex-wrap  content-center">*/}
        <img
          className="inline-block mt-28 hidden xl:block absolute bottom-0 right-0 h-5/6"
          style={{ height: '85%' }}
          src={process.env.PUBLIC_URL + '/body.png'}
        />
        {/*</div>*/}
      </div>
    </div>
  )
}
