import React, { useRef, useState } from 'react'
import Agendarse from './Registration'
import Login from './Login'
import ErrorWindow from './ErrorWindow'
import { FaWindowClose } from 'react-icons/all'
import { IconContext } from 'react-icons'
import UserSettings from "./UserSettings";

export default function _Modal({ Type, onClick }) {
  const [showModal, setShowModal] = React.useState(true)

  function closeModal() {
    setOpen(false)
    onClick(false)
    setShowModal(false)
  }

  function customCloseModal() {
    setOpen(false)
    onClick(false)
    setShowModal(false)
  }

  const [open, setOpen] = useState(true)

  const cancelButtonRef = useRef()

  const renderSwitch = (Type) => {
    const lowerCaseType = Type.toLowerCase()

    console.log(lowerCaseType)

    switch (lowerCaseType) {
      case 'login':
        return <Login />

      case 'agendarse':
        return <Agendarse onChange={(value) => customCloseModal()}/>

      case 'usersettings':
        return <UserSettings onChange={(value) => customCloseModal()}/>

      default:
        return <ErrorWindow />
    }
  }

  return (
    <>
      {/*<button
        className="bg-pink-500 text-white active:bg-pink-600 font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
        type="button"
        onClick={() => setShowModal(true)}
      >
        Open regular modal
      </button>*/}
      {/*   {()=> setShowModal(true)}*/}

      {showModal ? (
        <>
          <div className="fixed inset-0 z-50 outline-none focus:outline-none mt-10">
            <div className="relative w-auto my-6 mx-auto max-w-lg ">
              {/*content*/}
              <div className=" border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                <button className="p-1  mt-4 mr-4 ml-auto  border-0 text-black  float-right text-3xl leading-none font-semibold outline-none focus:outline-none">
                  <span className="mt-4 mr-4  text-black h-6 w-6 text-2xl block outline-none focus:outline-none">
                    <IconContext.Provider
                      value={{
                        style: {
                          fontSize: '30px',
                          color: 'rgb(205, 205, 205)',
                        },
                      }}
                    >
                      <FaWindowClose
                        cursor="pointer"
                        onClick={() => closeModal()}
                        onClose={closeModal}
                        className="pb-5 pt-4 absolute top-0 right-0 h-16 w-16 font-medium "
                      />
                    </IconContext.Provider>
                  </span>
                </button>

                {/*body*/}
                <div className="relative p-6 flex-auto w-full">
                  {renderSwitch(Type)}
                </div>
              </div>
            </div>
          </div>
          <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
        </>
      ) : null}
    </>
  )
}
