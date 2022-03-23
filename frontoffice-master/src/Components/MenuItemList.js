import { RiLoginCircleLine } from 'react-icons/all'
import { Link } from 'react-router-dom'
import SidebarData from './SidebarData'
import { Fragment } from 'react'

const MenuItemList = ({ role, onClick }) => {
  const url = 'http://vacunasuyg16.web.elasticloud.uy'
  // const url = 'http://localhost:8080'
  // const redirectUri = url //+ '/VacunasUYG16-api/api/logingubuy/authcode'
  const redirectUri = url + '/VacunasUYG16-api/api/logingubuy/authcode'
  // const redirectUri = 'http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/loginmobile'
  // http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/login.jsf

  const encodeUri = encodeURI(redirectUri)

  const src =
    'https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?scope=openid%20personal_info%20email&response_type=code&client_id=890192&redirect_uri=' +
    encodeUri +
    '&client_secret=457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179'

  function clickSignIn() {
    // onClick(true)
    // alert(src)
    window.location.href = src
  }

  return (
    <>
      {SidebarData({ role }).map((MenuItem, index) => (
        <li
          className="flex w-full justify-between text-denim-666 hover:text-blue-700 cursor-pointer
          items-center pb-5 mb-6 transition duration-500 ease-in-out transform hover:scale-110
          hover:animate-pulse"
          key={MenuItem}
        >
          <Link to={MenuItem.path} className="flex items-center">
            {MenuItem.icon}
            <span className="xl:text-lg md:text-2xl text-base ml-2 ">
              {' '}
              {MenuItem.title}
            </span>
          </Link>
        </li>
      ))}
      {role === '' ? (
        <li
          className="flex w-full justify-between text-denim-666 hover:text-blue-700 cursor-pointer
          items-center pb-5 mb-6 transition duration-500 ease-in-out transform hover:scale-110
          hover:animate-pulse"
          key={'ingresar'}
        >
          <div className="flex items-center" onClick={() => clickSignIn()}>
            <RiLoginCircleLine size="1.5em" />
            <span className="xl:text-lg md:text-2xl text-base ml-2 ">
              {' '}
              {'Ingresar'}
            </span>
          </div>
        </li>
      ) : (
        <Fragment />
      )}
    </>
  )
}

export default MenuItemList
