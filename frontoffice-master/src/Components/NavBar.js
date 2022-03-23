import React, { Fragment, useEffect, useState } from 'react'
import MainReservation from './MainReservation'
import MenuItemList from './MenuItemList'
import LoginGubUy from './LoginGubUy'
import { getSession, logOut } from './SessionService'
import { Link, Redirect, Route, Switch, useParams } from 'react-router-dom'
import Home from './Home'
import { Cookies, useCookies } from 'react-cookie'
import _Modal from './_Modal'
import Schedule from './Schedule'
import Monitor from './Monitor'
import VaccinatorSchedule from './VaccinatorSchedule'
import Modal from './Modal'
import Chat from './Chat'

export default function IndexPage({ onChange }) {
  const [chatPing, setChatPing] = useState(false)
  const [show, setShow] = useState(false)
  const [showModal, setShowModal] = useState(false)
  const [profile, setProfile] = useState(false)
  const [session, setSession] = useState(null)
  const [ci, setCi] = useState('')
  const [user, setUser] = useState('Jhon Test')
  const [rol, setRol] = useState('')
  const [idToken, setIdToken] = useState(undefined)
  const [image, setImage] = useState(
    'https://st4.depositphotos.com/4329009/19956/v/600/depositphotos_199564354-stock-illustration-creative-vector-illustration-default-avatar.jpg',
  )
  const [reload, setReload] = useState(false)
  const [prevJwt, setPrevJwt] = useState(undefined)

  const c = new Cookies()

  useEffect(() => {
    const cook = c.get('__FOsession')

    if (cook !== undefined) {
      setUser(cook.userName.toString())
      setRol(cook.role.toString())
      setCi(cook.ci.toString())
      setIdToken(cook.id_token.toString())
    }
  }, [])

  function refreshCookies(flg, token) {
    setReload(flg)
    setPrevJwt(token)
    const a = c.get('__FOsession')
    if (flg) {
      flg = false
      setReload(false)
      if (a !== undefined) {
        setUser(a.userName.toString())
        setRol(a.role.toString())
        setCi(a.ci.toString())
        setIdToken(a.id_token.toString())
      }
    }
  }

  async function funcLogout() {
    await logOut(idToken)
  }

  return (
    <>
      <div
        className="w-full h-full bg-fixed bg-no-repeat"
        style={{
          backgroundImage: `url("Hexa_3.png")`,
          webkitBackgroundSize: 'cover',
          mozBackgroundSize: 'cover',
          oBackgroundSize: 'cover',
          backgroundSize: 'cover',
        }}
      >
        <div className="flex flex-no-wrap">
          {/* Sidebar starts */}
          <div
            className="w-64 lg:relative bg-denim-400 shadow h-screen flex-col justify-between hidden lg:flex pb-12"
            style={{ backgroundImage: `url("tri_3.png")` }}
          >
            <div className="px-8">
              <div className="h-16 w-full flex items-center">
                <Link to="./">
                  <img
                    src="/logo_transparent.png"
                    width={144}
                    height={30}
                    viewBox="0 0 144 30"
                  />
                </Link>
              </div>
              <ul className="mt-12">
                <MenuItemList
                  role={rol}
                  onClick={(value) => setShowModal(value)}
                />
              </ul>
            </div>
          </div>

          {/*Mobile responsive sidebar*/}
          <div
            className={
              show
                ? 'w-full h-full absolute z-40  transform  translate-x-0 '
                : '   w-full h-full absolute z-40  transform -translate-x-full'
            }
          >
            <div
              className="bg-gray-800 opacity-50 w-full h-full absolute"
              onClick={() => setShow(!show)}
            ></div>
            <div className="w-64 md:w-96 absolute z-40 bg-denim-400 shadow h-full flex-col justify-between lg:hidden pb-4 transition duration-150 ease-in-out">
              <div className="flex flex-col justify-between h-full">
                <div>
                  <div className="flex items-center justify-between px-8">
                    <div className="h-16 w-full flex items-center">
                      <Link to="./">
                        <img
                          src="/logo_transparent.png"
                          width={144}
                          height={30}
                          viewBox="0 0 144 30"
                        />
                      </Link>
                    </div>
                    <div
                      id="closeSideBar"
                      className="flex items-center justify-center h-10 w-10"
                      onClick={() => setShow(!show)}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="icon icon-tabler icon-tabler-x"
                        width={20}
                        height={20}
                        viewBox="0 0 24 24"
                        strokeWidth="1.5"
                        stroke="currentColor"
                        fill="none"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                      >
                        <path stroke="none" d="M0 0h24v24H0z" />
                        <line x1={18} y1={6} x2={6} y2={18} />
                        <line x1={6} y1={6} x2={18} y2={18} />
                      </svg>
                    </div>
                  </div>

                  <div className="px-8">
                    <ul className="mt-12">
                      <MenuItemList role={rol} />
                    </ul>
                  </div>
                </div>
                <div className="w-full">
                  <div className="flex justify-center mb-4 w-full px-6">
                    <div className="relative w-full">
                      {/*<div className="text-gray-500 absolute ml-4 inset-0 m-auto w-4 h-4">*/}
                      {/*  <svg*/}
                      {/*    xmlns="http://www.w3.org/2000/svg"*/}
                      {/*    className="icon icon-tabler icon-tabler-search"*/}
                      {/*    width={16}*/}
                      {/*    height={16}*/}
                      {/*    viewBox="0 0 24 24"*/}
                      {/*    strokeWidth={1}*/}
                      {/*    stroke="#A0AEC0"*/}
                      {/*    fill="none"*/}
                      {/*    strokeLinecap="round"*/}
                      {/*    strokeLinejoin="round"*/}
                      {/*  >*/}
                      {/*    <path stroke="none" d="M0 0h24v24H0z" />*/}
                      {/*    <circle cx={10} cy={10} r={7} />*/}
                      {/*    <line x1={21} y1={21} x2={15} y2={15} />*/}
                      {/*  </svg>*/}
                      {/*</div>*/}
                      {/*<input*/}
                      {/*  className="bg-gray-100 focus:outline-none rounded w-full text-sm text-gray-500 bg-gray-100 pl-10 py-2"*/}
                      {/*  type="text"*/}
                      {/*  placeholder="Search"*/}
                      {/*/>*/}
                    </div>
                  </div>
                  <div className="border-t border-gray-300">
                    <div className="w-full flex items-center justify-between px-6 pt-1">
                      {rol !== '' ? (
                        <div className="flex items-center">
                          <img
                            alt="profile-pic"
                            src={image}
                            className="w-8 h-8 rounded-md"
                          />
                          <p className="md:text-xl text-denim-666 text-base leading-4 ml-2">
                            {rol === '' ? 'Invitado' : user}
                          </p>
                        </div>
                      ) : (
                        <div />
                      )}

                      <div className="h-full w-20 flex items-center justify-center">
                        <div className="relative text-denim-666">
                          <label className="text-base leading-normal mt-0 mb-2">
                            {rol !== ''
                              ? rol.toLowerCase() === 'citizen'
                                ? 'Ciudadano'
                                : 'Vacunador'
                              : 'Invitado'}
                          </label>
                        </div>
                      </div>
                      <ul className="flex">
                        {rol === 'Vaccinator' ? (
                          <></>
                        ) : (
                          <li className="cursor-pointer text-white pt-5 pb-3">
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              className="icon icon-tabler icon-tabler-messages"
                              width={24}
                              height={24}
                              viewBox="0 0 24 24"
                              strokeWidth={1}
                              stroke="#718096"
                              fill="none"
                              strokeLinecap="round"
                              strokeLinejoin="round"
                            >
                              <path stroke="none" d="M0 0h24v24H0z" />
                              <path d="M21 14l-3 -3h-7a1 1 0 0 1 -1 -1v-6a1 1 0 0 1 1 -1h9a1 1 0 0 1 1 1v10" />
                              <path d="M14 15v2a1 1 0 0 1 -1 1h-7l-3 3v-10a1 1 0 0 1 1 -1h2" />
                            </svg>
                          </li>
                          // <li />
                        )}

                        {rol !== '' ? (
                          <li className="cursor-pointer text-white pt-5 pb-3 pl-3">
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              className="icon icon-tabler icon-tabler-bell"
                              width={24}
                              height={24}
                              viewBox="0 0 24 24"
                              strokeWidth={1}
                              stroke="#718096"
                              fill="none"
                              strokeLinecap="round"
                              strokeLinejoin="round"
                            >
                              <path stroke="none" d="M0 0h24v24H0z" />
                              <path d="M10 5a2 2 0 0 1 4 0a7 7 0 0 1 4 6v3a4 4 0 0 0 2 3h-16a4 4 0 0 0 2 -3v-3a7 7 0 0 1 4 -6" />
                              <path d="M9 17v1a3 3 0 0 0 6 0v-1" />
                            </svg>
                          </li>
                        ) : (
                          <li></li>
                        )}
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          {/*Mobile responsive sidebar*/}
          {/* Sidebar ends */}

          <div className="w-full">
            {/* Navigation starts */}
            <nav
              className="h-16 flex items-center lg:items-stretch justify-end lg:justify-between bg-denim-400 shadow relative z-0"
              style={{ backgroundImage: `url("background_navbar.png")` }}
            >
              <div className="hidden lg:flex w-full pr-6">
                <div className="w-1/2 h-full hidden lg:flex items-center pl-6 pr-24">
                  {/*<div className="relative w-full">*/}
                  {/*  <div className="text-gray-500 absolute ml-4 inset-0 m-auto w-4 h-4">*/}
                  {/*    <svg*/}
                  {/*      xmlns="http://www.w3.org/2000/svg"*/}
                  {/*      className="icon icon-tabler icon-tabler-search"*/}
                  {/*      width={16}*/}
                  {/*      height={16}*/}
                  {/*      viewBox="0 0 24 24"*/}
                  {/*      strokeWidth="1.5"*/}
                  {/*      stroke="currentColor"*/}
                  {/*      fill="none"*/}
                  {/*      strokeLinecap="round"*/}
                  {/*      strokeLinejoin="round"*/}
                  {/*    >*/}
                  {/*      <path stroke="none" d="M0 0h24v24H0z" />*/}
                  {/*      <circle cx={10} cy={10} r={7} />*/}
                  {/*      <line x1={21} y1={21} x2={15} y2={15} />*/}
                  {/*    </svg>*/}
                  {/*  </div>*/}
                  {/*  <input*/}
                  {/*    className="border border-gray-100 focus:outline-none focus:border-indigo-700 rounded w-full text-sm text-gray-500 bg-gray-100 pl-12 py-2"*/}
                  {/*    type="text"*/}
                  {/*    placeholder="Search"*/}
                  {/*  />*/}
                  {/*</div>*/}
                </div>
                <div className="w-1/2 hidden lg:flex">
                  <div className="w-full flex items-center pl-8 justify-end">
                    <div className="h-full w-20 flex items-center mr-4 justify-center ">
                      <div className="relative text-denim-666">
                        <label className="text-2xl leading-normal pr-9 mt-0 mb-2">
                          {rol !== ''
                            ? rol.toLowerCase() === 'citizen'
                              ? 'Ciudadano'
                              : 'Vacunador'
                            : 'Invitado'}
                        </label>
                      </div>
                    </div>

                    {rol !== '' ? (
                      <>
                        {/*<div*/}
                        {/*    className="h-full w-20 flex items-center justify-center cursor-pointer text-denim-666">*/}
                        {/*    <div className="relative cursor-pointer text-denim-666">*/}
                        {/*        <svg*/}
                        {/*            xmlns="http://www.w3.org/2000/svg"*/}
                        {/*            className="icon icon-tabler icon-tabler-bell"*/}
                        {/*            width={28}*/}
                        {/*            height={28}*/}
                        {/*            viewBox="0 0 24 24"*/}
                        {/*            strokeWidth="1.5"*/}
                        {/*            stroke="currentColor"*/}
                        {/*            fill="none"*/}
                        {/*            strokeLinecap="round"*/}
                        {/*            strokeLinejoin="round"*/}
                        {/*        >*/}
                        {/*            <path stroke="none" d="M0 0h24v24H0z"/>*/}
                        {/*            <path*/}
                        {/*                d="M10 5a2 2 0 0 1 4 0a7 7 0 0 1 4 6v3a4 4 0 0 0 2 3h-16a4 4 0 0 0 2 -3v-3a7 7 0 0 1 4 -6"/>*/}
                        {/*            <path d="M9 17v1a3 3 0 0 0 6 0v-1"/>*/}
                        {/*        </svg>*/}
                        {/*        <div className="animate-ping  w-2 h-2 rounded-full bg-red-400 border border-white absolute inset-0 mt-1 mr-1 m-auto"/>*/}
                        {/*</div>*/}
                        {/*</div>*/}
                      </>
                    ) : (
                      <div />
                    )}

                    {rol === 'Vaccinator' ? (
                      <Link
                        to="/chat"
                        className="h-full w-20 flex items-center justify-center mr-4 cursor-pointer text-denim-666 "
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          className="icon icon-tabler icon-tabler-messages"
                          width={28}
                          height={28}
                          viewBox="0 0 24 24"
                          strokeWidth="1.5"
                          stroke="currentColor"
                          fill="none"
                          strokeLinecap="round"
                          strokeLinejoin="round"
                        >
                          <path stroke="none" d="M0 0h24v24H0z" />
                          <path d="M21 14l-3 -3h-7a1 1 0 0 1 -1 -1v-6a1 1 0 0 1 1 -1h9a1 1 0 0 1 1 1v10" />
                          <path d="M14 15v2a1 1 0 0 1 -1 1h-7l-3 3v-10a1 1 0 0 1 1 -1h2" />
                        </svg>
                        {!chatPing ? (
                          <div className="animate-ping  w-2 h-2 rounded-full bg-red-400 border border-white mb-5" />
                        ) : (
                          <></>
                        )}
                      </Link>
                    ) : (
                      <div />
                    )}

                    {rol !== '' ? (
                      <div
                        className="flex items-center relative cursor-pointer"
                        onClick={() => setProfile(!profile)}
                      >
                        <div className="rounded-full">
                          {profile ? (
                            <ul className="p-2 w-full border-r bg-denim-400 absolute rounded left-0 shadow mt-12 sm:mt-16 ">
                              <li className="flex w-full justify-between text-denim-666 hover:text-blue-700 cursor-pointer items-center">
                                <div className="flex items-center">
                                  <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    className="icon icon-tabler icon-tabler-user"
                                    width={18}
                                    height={18}
                                    viewBox="0 0 24 24"
                                    strokeWidth="1.5"
                                    stroke="currentColor"
                                    fill="none"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                  >
                                    <path stroke="none" d="M0 0h24v24H0z" />
                                    <circle cx={12} cy={7} r={4} />
                                    <path d="M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
                                  </svg>
                                  <div onClick={() => setShowModal(true)}>
                                    <span className="text-sm ml-2">Perfil</span>
                                  </div>
                                </div>
                              </li>
                              <li className="flex w-full justify-between text-denim-666 hover:text-blue-700 cursor-pointer items-center mt-2">
                                <div className="flex items-center">
                                  <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    className="icon icon-tabler icon-tabler-logout"
                                    width={20}
                                    height={20}
                                    viewBox="0 0 24 24"
                                    strokeWidth="1.5"
                                    stroke="currentColor"
                                    fill="none"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                  >
                                    <path stroke="none" d="M0 0h24v24H0z" />
                                    <path d="M14 8v-2a2 2 0 0 0 -2 -2h-7a2 2 0 0 0 -2 2v12a2 2 0 0 0 2 2h7a2 2 0 0 0 2 -2v-2" />
                                    <path d="M7 12h14l-3 -3m0 6l3 -3" />
                                  </svg>
                                  <div onClick={async () => await funcLogout()}>
                                    <span className="text-sm ml-2">Salir</span>
                                  </div>
                                </div>
                              </li>
                            </ul>
                          ) : (
                            ''
                          )}

                          <div className="relative">
                            <img
                              className="rounded-full h-10 w-10 object-cover"
                              src={image}
                              alt="avatar"
                            />
                            <div className="w-2 h-2 rounded-full bg-green-400 border border-white absolute inset-0 mb-0 mr-0 m-auto" />
                          </div>
                        </div>

                        <p className="text-denim-666 text-sm mx-3">
                          {rol === '' ? 'Invitado' : user}
                        </p>

                        <div className="cursor-pointer text-denim-666">
                          <svg
                            aria-haspopup="true"
                            xmlns="http://www.w3.org/2000/svg"
                            className="icon icon-tabler icon-tabler-chevron-down"
                            width={20}
                            height={20}
                            viewBox="0 0 24 24"
                            strokeWidth="1.5"
                            stroke="currentColor"
                            fill="none"
                            strokeLinecap="round"
                            strokeLinejoin="round"
                          >
                            <path stroke="none" d="M0 0h24v24H0z" />
                            <polyline points="6 9 12 15 18 9" />
                          </svg>
                        </div>
                      </div>
                    ) : (
                      <div />
                    )}
                  </div>
                </div>
              </div>
              <div
                className="text-denim-666 mr-8 visible lg:hidden relative"
                onClick={() => setShow(!show)}
                id="menu"
              >
                {show ? (
                  ''
                ) : (
                  <svg
                    aria-label="Main Menu"
                    aria-haspopup="true"
                    xmlns="http://www.w3.org/2000/svg"
                    className="icon icon-tabler icon-tabler-menu cursor-pointer"
                    width={30}
                    height={30}
                    viewBox="0 0 24 24"
                    strokeWidth="1.5"
                    stroke="currentColor"
                    fill="none"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                  >
                    <path stroke="none" d="M0 0h24v24H0z" />
                    <line x1={4} y1={8} x2={20} y2={8} />
                    <line x1={4} y1={16} x2={20} y2={16} />
                  </svg>
                )}
              </div>
            </nav>
            {/* Navigation ends */}
            {/* Remove class [ h-64 ] when adding a card block */}
            <div className="container mx-auto py-10 h-64 md:w-4/5 w-11/12 px-6">
              {/* Remove class [ border-dashed border-2 border-gray-300 ] to remove dotted border */}
              <div className="w-full h-full rounded">
                <Switch>
                  <Route path="/" exact component={Home} />
                  <Route path="/schedule" component={Schedule} />
                  <Route path="/monitor" component={Monitor} />
                  <Route path="/LogGobUy/:jwt" children={<Child />} />
                  {/*{ !getSession() && <Route path="/reservation" component={MainReservation} /> }*/}
                  {!getSession() ? ( //FALTARÍA AGREGAR AUTORIZACIÓN
                    <Fragment>
                      <Route path="/reservation" component={MainReservation} />
                      <Route
                        path="/vaccinatorschedule"
                        component={VaccinatorSchedule}
                      />
                      <Route path="/chat" component={Chat} />
                      <Route path="/login" component={LoginGubUy} />
                    </Fragment>
                  ) : (
                    <Fragment />
                  )}
                </Switch>
              </div>
            </div>
          </div>
        </div>
      </div>
      {showModal ? (
        <Fragment>
          {/*<Modal Type={'login'} onClick={(value) => setShowModal(value)} />*/}
          <_Modal
            Type={'usersettings'}
            classN="w-full h-screen container border"
            onClick={(value) => setShowModal(value)}
          />
        </Fragment>
      ) : null}
    </>
  )

  function Child() {
    // We can use the `useParams` hook here to access
    // the dynamic pieces of the URL.

    let { jwt } = useParams()

    if (jwt !== prevJwt) {
      // setPrevJwt(jwt)
      return (
        <>
          <LoginGubUy
            token={jwt}
            onChange={(flg, token) => {
              refreshCookies(flg, token)
            }}
          />
        </>
      )
    } else {
      window.location.href = '/'
      return <Link to="./" />
    }
  }
}
