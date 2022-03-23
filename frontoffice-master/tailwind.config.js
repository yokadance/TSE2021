module.exports = {
  purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        denim: {
          50: '#f7f9fb',
          100: '#e7f0fc',
          200: '#cdd8fa',
          300: '#a7b3f1',
          400: '#7AA5D2',
          444: '#75AFD6',
          500: '#6e67df',
          555: '#3786BB',
          600: '#5b4acf',
          666: '#eeeeee',
          700: '#4538ae',
          800: '#302680',
          900: '#1b184f',
        },
      },
    },
  },
  variants: {
    extend: {
      animation: ['hover', 'focus'],
      transitionProperty: ['responsive', 'motion-safe', 'motion-reduce'],
      transitionDuration: ['hover', 'focus'],
      transitionTimingFunction: ['hover', 'focus'],
      transitionDelay: ['hover', 'focus'],
      opacity: ['disabled'],
    },
  },
  plugins: [],
}
