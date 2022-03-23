/* This example requires Tailwind CSS v2.0+ */
import React, { Fragment, useRef, useState } from 'react'
import { Dialog, Transition } from '@headlessui/react'
import Login from './Login'
import Agendarse from './Registration'
import ErrorWindow from './ErrorWindow'
import { FaWindowClose } from 'react-icons/all'
import { IconContext } from 'react-icons'
import MultiRole from './MultiRole'
import UserSettings from './UserSettings'

export default function Modal({
  Type,
  onClick,
  src,
  classN,
  flgClose,
  value,
  messageTitle,
}) {
  const [open, setOpen] = useState(true)
  const [showModal, setShowModal] = React.useState(true)

  const cancelButtonRef = useRef()

  function closeModal() {
    setOpen(false)
    onClick(false)
    setShowModal(false)
  }

  function setValClick(val) {
    setOpen(false)
    setShowModal(false)
    onClick(val)
  }

  function noClose() {}

  const renderSwitch = (Type) => {
    const lowerCaseType = Type.toLowerCase()

    switch (lowerCaseType) {
      case 'login':
        return <Login />

      case 'agendarse':
        return <Agendarse />

      case 'iframe':
        return <iframe src={src} className={classN} />

      case 'multirole':
        return <MultiRole onClick={(val) => setValClick(val)} />

      case 'usersettings':
        return (
          <UserSettings
            messageTitle={messageTitle}
            onChange={(value) => closeModal()}
          />
        )

      default:
        return <ErrorWindow />
    }
  }

  return (
    <Transition.Root show={open} as={Fragment}>
      <Dialog
        as="div"
        static
        className="fixed z-10 inset-0 overflow-y-auto"
        initialFocus={cancelButtonRef}
        open={open}
        onClose={flgClose === false ? noClose : closeModal}
      >
        <div className="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <Dialog.Overlay className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
          </Transition.Child>

          {/* This element is to trick the browser into centering the modal contents. */}
          <span
            className="hidden sm:inline-block sm:align-middle sm:h-screen"
            aria-hidden="true"
          >
            &#8203;
          </span>
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            enterTo="opacity-100 translate-y-0 sm:scale-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100 translate-y-0 sm:scale-100"
            leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
          >
            <div
              className="inline-block align-bottom bg-transparent rounded-lg text-left
             overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle
             sm:max-w-lg sm:w-full"
            >
              {flgClose === false ? (
                <div />
              ) : (
                <div className="bg-white px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
                  <IconContext.Provider
                    value={{
                      style: { fontSize: '30px', color: 'rgb(205, 205, 205)' },
                    }}
                  >
                    <FaWindowClose
                      cursor="pointer"
                      onClick={() => closeModal()}
                    />
                  </IconContext.Provider>
                </div>
              )}

              <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                {renderSwitch(Type)}
              </div>
            </div>
          </Transition.Child>
        </div>
      </Dialog>
    </Transition.Root>
  )
}
