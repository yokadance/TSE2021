function _interopDefault(ex) {
  return ex && typeof ex === 'object' && 'default' in ex ? ex['default'] : ex
}

var React = _interopDefault(require('react'))

function _extends() {
  _extends =
    Object.assign ||
    function (target) {
      for (var i = 1; i < arguments.length; i++) {
        var source = arguments[i]

        for (var key in source) {
          if (Object.prototype.hasOwnProperty.call(source, key)) {
            target[key] = source[key]
          }
        }
      }

      return target
    }

  return _extends.apply(this, arguments)
}

function _inheritsLoose(subClass, superClass) {
  subClass.prototype = Object.create(superClass.prototype)
  subClass.prototype.constructor = subClass
  subClass.__proto__ = superClass
}

function ThreeDotsvg(props) {
  var _props$className

  return React.createElement(
    'svg',
    {
      width: '14',
      height: '4',
      className:
        '' +
        ((_props$className = props.className) != null ? _props$className : ''),
      viewBox: '0 0 14 4',
      fill: 'none',
      xmlns: 'http://www.w3.org/2000/svg',
    },
    React.createElement(
      'g',
      {
        opacity: '0.4',
      },
      React.createElement('circle', {
        cx: '2.19796',
        cy: '1.80139',
        r: '1.38611',
        fill: '#222222',
      }),
      React.createElement('circle', {
        cx: '11.9013',
        cy: '1.80115',
        r: '1.38611',
        fill: '#222222',
      }),
      React.createElement('circle', {
        cx: '7.04991',
        cy: '1.80115',
        r: '1.38611',
        fill: '#222222',
      }),
    ),
  )
}

function LeftSvg(props) {
  var _props$className

  return React.createElement(
    'svg',
    {
      onClick: props.onClick,
      className:
        '' +
        ((_props$className = props.className) != null ? _props$className : ''),
      xmlns: 'http://www.w3.org/2000/svg',
      viewBox: '0 0 492 492',
    },
    React.createElement('path', {
      d:
        'M198.608 246.104L382.664 62.04c5.068-5.056 7.856-11.816 7.856-19.024 0-7.212-2.788-13.968-7.856-19.032l-16.128-16.12C361.476 2.792 354.712 0 347.504 0s-13.964 2.792-19.028 7.864L109.328 227.008c-5.084 5.08-7.868 11.868-7.848 19.084-.02 7.248 2.76 14.028 7.848 19.112l218.944 218.932c5.064 5.072 11.82 7.864 19.032 7.864 7.208 0 13.964-2.792 19.032-7.864l16.124-16.12c10.492-10.492 10.492-27.572 0-38.06L198.608 246.104z',
    }),
  )
}

function RightSvg(props) {
  var _props$className

  return React.createElement(
    'svg',
    {
      onClick: props.onClick,
      className:
        '' +
        ((_props$className = props.className) != null ? _props$className : ''),
      viewBox: '0 0 492.004 492.004',
      xmlns: 'http://www.w3.org/2000/svg',
    },
    React.createElement('path', {
      d:
        'M382.678 226.804L163.73 7.86C158.666 2.792 151.906 0 144.698 0s-13.968 2.792-19.032 7.86l-16.124 16.12c-10.492 10.504-10.492 27.576 0 38.064L293.398 245.9l-184.06 184.06c-5.064 5.068-7.86 11.824-7.86 19.028 0 7.212 2.796 13.968 7.86 19.04l16.124 16.116c5.068 5.068 11.824 7.86 19.032 7.86s13.968-2.792 19.032-7.86L382.678 265c5.076-5.084 7.864-11.872 7.848-19.088.016-7.244-2.772-14.028-7.848-19.108z',
    }),
  )
}

var utils = (function () {
  var utils = /*#__PURE__*/ (function () {
    function utils() {}

    utils.limit = function limit(func, wait, debounce) {
      var timeout
      return function (input) {
        var context = this,
          args = arguments

        var throttler = function throttler() {
          timeout = undefined
          func.apply(context, args)
        }

        if (debounce) window.clearTimeout(timeout)
        if (debounce || !timeout) timeout = window.setTimeout(throttler, wait)
      }
    }

    utils.unwindObject = function unwindObject(obj, field) {
      var fields = field.split('.')
      var field_length = fields.length

      if (field_length <= 1) {
        return obj[field]
      }

      var recuObje = _extends({}, obj)

      var rolled_time = 0

      while (rolled_time < field_length) {
        recuObje = recuObje[fields[rolled_time]]
        rolled_time++
      }

      return recuObje
    }

    utils.TableNumberLinks = function TableNumberLinks(rows, per_page) {
      if (per_page === void 0) {
        per_page = 2
      }

      var page_maximum = per_page
      var rows_array = rows.concat()
      var rows_length = rows_array.length
      var number_of_pages = Math.ceil(rows_length / page_maximum)
      var end_of_this_record,
        beginning_of_next_record = 0,
        remaining_rows,
        current_page = 0,
        page_number_store = [],
        page_map = {}

      for (var i = 0; i < number_of_pages; ++i) {
        current_page++
        end_of_this_record = page_maximum * current_page
        remaining_rows = rows_length - end_of_this_record
        page_number_store.push(current_page)
        var page_row_array = rows_array.slice(
          beginning_of_next_record,
          end_of_this_record,
        )
        page_map[current_page] = {
          page_row_array: page_row_array,
          back_button_clickable: current_page === 1 ? false : true,
          forward_button_clickable: false,
          is_active: false,
        }

        if (remaining_rows > 0) {
          page_map[current_page].forward_button_clickable = true
        }

        page_row_array = null
        beginning_of_next_record = end_of_this_record
      }

      return {
        page_map: page_map,
        page_number_store: page_number_store,
        all_rows: rows_array,
      }
    }

    utils.isObjectEqual = function isObjectEqual(value, other) {
      var type = Object.prototype.toString.call(value)
      if (type !== Object.prototype.toString.call(other)) return false
      if (['[object Array]', '[object Object]'].indexOf(type) < 0) return false
      var valueLen =
        type === '[object Array]' ? value.length : Object.keys(value).length
      var otherLen =
        type === '[object Array]' ? other.length : Object.keys(other).length
      if (valueLen !== otherLen) return false
      if (value === other) return true

      if (type === '[object Array]') {
        for (var i = 0; i < valueLen; i++) {
          if (utils.compare(value[i], other[i]) === false) return false
        }
      } else {
        for (var key in value) {
          if (value.hasOwnProperty(key)) {
            if (utils.compare(value[key], other[key]) === false) return false
          }
        }
      }

      return true
    }

    utils.compare = function compare(item1, item2) {
      var itemType = Object.prototype.toString.call(item1)

      if (itemType === '[object Array]') {
        if (!utils.isObjectEqual(item1, item2)) return false
      }

      if (itemType === '[object Object]') {
        if (!utils.isObjectEqual(item1, item2)) return false
      } else {
        if (itemType !== Object.prototype.toString.call(item2)) return false

        if (itemType === '[object Function]') {
          if (item1.toString() !== item2.toString()) return false
        } else {
          if (item1 !== item2) return false
        }
      }

      return true
    }

    utils.stringifyObjectValues = function stringifyObjectValues(val) {
      if (typeof val === 'undefined' || val === null) {
        return ''
      }

      if (val instanceof Object && !(val instanceof Date)) {
        return Object.keys(val)
          .sort()
          .filter(function (v) {
            return v !== undefined && v !== null
          })
          .map(function (k) {
            return utils.stringifyObjectValues(val[k])
          })
          .join(' ')
      }

      return String(val)
    }

    utils.sanitizeRow = function sanitizeRow(row, columns) {
      var final_obj = {}

      for (var i = 0, len = columns.length; i < len; ++i) {
        if (columns[i].use_in_search === false) {
          continue
        }

        final_obj[columns[i].field] = utils.unwindObject(row, columns[i].field)
      }

      return final_obj
    }

    utils.stringifyRowValues = function stringifyRowValues(row, column) {
      return typeof row === 'object' && row !== null
        ? utils.stringifyObjectValues(utils.sanitizeRow(row, column))
        : ''
    }

    utils.filterFunction = function filterFunction(search_string, columns) {
      return function ffn(row) {
        var regExp = utils.stringtoRegEx(search_string)
        return regExp.test(utils.stringifyRowValues(row, columns))
      }
    }

    utils.stringtoRegEx = function stringtoRegEx(str) {
      str = utils.matchMultipleSpace(utils.escapeRegExp(str))
      return new RegExp('.*' + str + '.*', 'i')
    }

    utils.matchMultipleSpace = function matchMultipleSpace(str) {
      return str.replace(utils.RX_SPACES$1, '\\s+')
    }

    utils.escapeRegExp = function escapeRegExp(text) {
      return text.replace(/[-/\\^$*+?.()|[\]{}]/g, '\\$&')
    }

    return utils
  })()

  utils.throttle = function (func, wait) {
    return utils.limit(func, wait, false)
  }

  utils.debounce = function (func, wait) {
    return utils.limit(func, wait, true)
  }

  utils.RX_SPACES$1 = /[\s\uFEFF\xA0]+/g
  return utils
})()

function createCommonjsModule(fn, module) {
  return (module = { exports: {} }), fn(module, module.exports), module.exports
}

/** @license React v16.13.1
 * react-is.production.min.js
 *
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
var b = 'function' === typeof Symbol && Symbol.for,
  c = b ? Symbol.for('react.element') : 60103,
  d = b ? Symbol.for('react.portal') : 60106,
  e = b ? Symbol.for('react.fragment') : 60107,
  f = b ? Symbol.for('react.strict_mode') : 60108,
  g = b ? Symbol.for('react.profiler') : 60114,
  h = b ? Symbol.for('react.provider') : 60109,
  k = b ? Symbol.for('react.context') : 60110,
  l = b ? Symbol.for('react.async_mode') : 60111,
  m = b ? Symbol.for('react.concurrent_mode') : 60111,
  n = b ? Symbol.for('react.forward_ref') : 60112,
  p = b ? Symbol.for('react.suspense') : 60113,
  q = b ? Symbol.for('react.suspense_list') : 60120,
  r = b ? Symbol.for('react.memo') : 60115,
  t = b ? Symbol.for('react.lazy') : 60116,
  v = b ? Symbol.for('react.block') : 60121,
  w = b ? Symbol.for('react.fundamental') : 60117,
  x = b ? Symbol.for('react.responder') : 60118,
  y = b ? Symbol.for('react.scope') : 60119
function z(a) {
  if ('object' === typeof a && null !== a) {
    var u = a.$$typeof
    switch (u) {
      case c:
        switch (((a = a.type), a)) {
          case l:
          case m:
          case e:
          case g:
          case f:
          case p:
            return a
          default:
            switch (((a = a && a.$$typeof), a)) {
              case k:
              case n:
              case t:
              case r:
              case h:
                return a
              default:
                return u
            }
        }
      case d:
        return u
    }
  }
}
function A(a) {
  return z(a) === m
}
var AsyncMode = l
var ConcurrentMode = m
var ContextConsumer = k
var ContextProvider = h
var Element = c
var ForwardRef = n
var Fragment = e
var Lazy = t
var Memo = r
var Portal = d
var Profiler = g
var StrictMode = f
var Suspense = p
var isAsyncMode = function (a) {
  return A(a) || z(a) === l
}
var isConcurrentMode = A
var isContextConsumer = function (a) {
  return z(a) === k
}
var isContextProvider = function (a) {
  return z(a) === h
}
var isElement = function (a) {
  return 'object' === typeof a && null !== a && a.$$typeof === c
}
var isForwardRef = function (a) {
  return z(a) === n
}
var isFragment = function (a) {
  return z(a) === e
}
var isLazy = function (a) {
  return z(a) === t
}
var isMemo = function (a) {
  return z(a) === r
}
var isPortal = function (a) {
  return z(a) === d
}
var isProfiler = function (a) {
  return z(a) === g
}
var isStrictMode = function (a) {
  return z(a) === f
}
var isSuspense = function (a) {
  return z(a) === p
}
var isValidElementType = function (a) {
  return (
    'string' === typeof a ||
    'function' === typeof a ||
    a === e ||
    a === m ||
    a === g ||
    a === f ||
    a === p ||
    a === q ||
    ('object' === typeof a &&
      null !== a &&
      (a.$$typeof === t ||
        a.$$typeof === r ||
        a.$$typeof === h ||
        a.$$typeof === k ||
        a.$$typeof === n ||
        a.$$typeof === w ||
        a.$$typeof === x ||
        a.$$typeof === y ||
        a.$$typeof === v))
  )
}
var typeOf = z

var reactIs_production_min = {
  AsyncMode: AsyncMode,
  ConcurrentMode: ConcurrentMode,
  ContextConsumer: ContextConsumer,
  ContextProvider: ContextProvider,
  Element: Element,
  ForwardRef: ForwardRef,
  Fragment: Fragment,
  Lazy: Lazy,
  Memo: Memo,
  Portal: Portal,
  Profiler: Profiler,
  StrictMode: StrictMode,
  Suspense: Suspense,
  isAsyncMode: isAsyncMode,
  isConcurrentMode: isConcurrentMode,
  isContextConsumer: isContextConsumer,
  isContextProvider: isContextProvider,
  isElement: isElement,
  isForwardRef: isForwardRef,
  isFragment: isFragment,
  isLazy: isLazy,
  isMemo: isMemo,
  isPortal: isPortal,
  isProfiler: isProfiler,
  isStrictMode: isStrictMode,
  isSuspense: isSuspense,
  isValidElementType: isValidElementType,
  typeOf: typeOf,
}

var reactIs_development = createCommonjsModule(function (module, exports) {
  if (process.env.NODE_ENV !== 'production') {
    ;(function () {
      // The Symbol used to tag the ReactElement-like types. If there is no native Symbol
      // nor polyfill, then a plain number is used for performance.
      var hasSymbol = typeof Symbol === 'function' && Symbol.for
      var REACT_ELEMENT_TYPE = hasSymbol ? Symbol.for('react.element') : 0xeac7
      var REACT_PORTAL_TYPE = hasSymbol ? Symbol.for('react.portal') : 0xeaca
      var REACT_FRAGMENT_TYPE = hasSymbol
        ? Symbol.for('react.fragment')
        : 0xeacb
      var REACT_STRICT_MODE_TYPE = hasSymbol
        ? Symbol.for('react.strict_mode')
        : 0xeacc
      var REACT_PROFILER_TYPE = hasSymbol
        ? Symbol.for('react.profiler')
        : 0xead2
      var REACT_PROVIDER_TYPE = hasSymbol
        ? Symbol.for('react.provider')
        : 0xeacd
      var REACT_CONTEXT_TYPE = hasSymbol ? Symbol.for('react.context') : 0xeace // TODO: We don't use AsyncMode or ConcurrentMode anymore. They were temporary
      // (unstable) APIs that have been removed. Can we remove the symbols?

      var REACT_ASYNC_MODE_TYPE = hasSymbol
        ? Symbol.for('react.async_mode')
        : 0xeacf
      var REACT_CONCURRENT_MODE_TYPE = hasSymbol
        ? Symbol.for('react.concurrent_mode')
        : 0xeacf
      var REACT_FORWARD_REF_TYPE = hasSymbol
        ? Symbol.for('react.forward_ref')
        : 0xead0
      var REACT_SUSPENSE_TYPE = hasSymbol
        ? Symbol.for('react.suspense')
        : 0xead1
      var REACT_SUSPENSE_LIST_TYPE = hasSymbol
        ? Symbol.for('react.suspense_list')
        : 0xead8
      var REACT_MEMO_TYPE = hasSymbol ? Symbol.for('react.memo') : 0xead3
      var REACT_LAZY_TYPE = hasSymbol ? Symbol.for('react.lazy') : 0xead4
      var REACT_BLOCK_TYPE = hasSymbol ? Symbol.for('react.block') : 0xead9
      var REACT_FUNDAMENTAL_TYPE = hasSymbol
        ? Symbol.for('react.fundamental')
        : 0xead5
      var REACT_RESPONDER_TYPE = hasSymbol
        ? Symbol.for('react.responder')
        : 0xead6
      var REACT_SCOPE_TYPE = hasSymbol ? Symbol.for('react.scope') : 0xead7

      function isValidElementType(type) {
        return (
          typeof type === 'string' ||
          typeof type === 'function' || // Note: its typeof might be other than 'symbol' or 'number' if it's a polyfill.
          type === REACT_FRAGMENT_TYPE ||
          type === REACT_CONCURRENT_MODE_TYPE ||
          type === REACT_PROFILER_TYPE ||
          type === REACT_STRICT_MODE_TYPE ||
          type === REACT_SUSPENSE_TYPE ||
          type === REACT_SUSPENSE_LIST_TYPE ||
          (typeof type === 'object' &&
            type !== null &&
            (type.$$typeof === REACT_LAZY_TYPE ||
              type.$$typeof === REACT_MEMO_TYPE ||
              type.$$typeof === REACT_PROVIDER_TYPE ||
              type.$$typeof === REACT_CONTEXT_TYPE ||
              type.$$typeof === REACT_FORWARD_REF_TYPE ||
              type.$$typeof === REACT_FUNDAMENTAL_TYPE ||
              type.$$typeof === REACT_RESPONDER_TYPE ||
              type.$$typeof === REACT_SCOPE_TYPE ||
              type.$$typeof === REACT_BLOCK_TYPE))
        )
      }

      function typeOf(object) {
        if (typeof object === 'object' && object !== null) {
          var $$typeof = object.$$typeof

          switch ($$typeof) {
            case REACT_ELEMENT_TYPE:
              var type = object.type

              switch (type) {
                case REACT_ASYNC_MODE_TYPE:
                case REACT_CONCURRENT_MODE_TYPE:
                case REACT_FRAGMENT_TYPE:
                case REACT_PROFILER_TYPE:
                case REACT_STRICT_MODE_TYPE:
                case REACT_SUSPENSE_TYPE:
                  return type

                default:
                  var $$typeofType = type && type.$$typeof

                  switch ($$typeofType) {
                    case REACT_CONTEXT_TYPE:
                    case REACT_FORWARD_REF_TYPE:
                    case REACT_LAZY_TYPE:
                    case REACT_MEMO_TYPE:
                    case REACT_PROVIDER_TYPE:
                      return $$typeofType

                    default:
                      return $$typeof
                  }
              }

            case REACT_PORTAL_TYPE:
              return $$typeof
          }
        }

        return undefined
      } // AsyncMode is deprecated along with isAsyncMode

      var AsyncMode = REACT_ASYNC_MODE_TYPE
      var ConcurrentMode = REACT_CONCURRENT_MODE_TYPE
      var ContextConsumer = REACT_CONTEXT_TYPE
      var ContextProvider = REACT_PROVIDER_TYPE
      var Element = REACT_ELEMENT_TYPE
      var ForwardRef = REACT_FORWARD_REF_TYPE
      var Fragment = REACT_FRAGMENT_TYPE
      var Lazy = REACT_LAZY_TYPE
      var Memo = REACT_MEMO_TYPE
      var Portal = REACT_PORTAL_TYPE
      var Profiler = REACT_PROFILER_TYPE
      var StrictMode = REACT_STRICT_MODE_TYPE
      var Suspense = REACT_SUSPENSE_TYPE
      var hasWarnedAboutDeprecatedIsAsyncMode = false // AsyncMode should be deprecated

      function isAsyncMode(object) {
        {
          if (!hasWarnedAboutDeprecatedIsAsyncMode) {
            hasWarnedAboutDeprecatedIsAsyncMode = true // Using console['warn'] to evade Babel and ESLint

            console['warn'](
              'The ReactIs.isAsyncMode() alias has been deprecated, ' +
                'and will be removed in React 17+. Update your code to use ' +
                'ReactIs.isConcurrentMode() instead. It has the exact same API.',
            )
          }
        }

        return (
          isConcurrentMode(object) || typeOf(object) === REACT_ASYNC_MODE_TYPE
        )
      }
      function isConcurrentMode(object) {
        return typeOf(object) === REACT_CONCURRENT_MODE_TYPE
      }
      function isContextConsumer(object) {
        return typeOf(object) === REACT_CONTEXT_TYPE
      }
      function isContextProvider(object) {
        return typeOf(object) === REACT_PROVIDER_TYPE
      }
      function isElement(object) {
        return (
          typeof object === 'object' &&
          object !== null &&
          object.$$typeof === REACT_ELEMENT_TYPE
        )
      }
      function isForwardRef(object) {
        return typeOf(object) === REACT_FORWARD_REF_TYPE
      }
      function isFragment(object) {
        return typeOf(object) === REACT_FRAGMENT_TYPE
      }
      function isLazy(object) {
        return typeOf(object) === REACT_LAZY_TYPE
      }
      function isMemo(object) {
        return typeOf(object) === REACT_MEMO_TYPE
      }
      function isPortal(object) {
        return typeOf(object) === REACT_PORTAL_TYPE
      }
      function isProfiler(object) {
        return typeOf(object) === REACT_PROFILER_TYPE
      }
      function isStrictMode(object) {
        return typeOf(object) === REACT_STRICT_MODE_TYPE
      }
      function isSuspense(object) {
        return typeOf(object) === REACT_SUSPENSE_TYPE
      }

      exports.AsyncMode = AsyncMode
      exports.ConcurrentMode = ConcurrentMode
      exports.ContextConsumer = ContextConsumer
      exports.ContextProvider = ContextProvider
      exports.Element = Element
      exports.ForwardRef = ForwardRef
      exports.Fragment = Fragment
      exports.Lazy = Lazy
      exports.Memo = Memo
      exports.Portal = Portal
      exports.Profiler = Profiler
      exports.StrictMode = StrictMode
      exports.Suspense = Suspense
      exports.isAsyncMode = isAsyncMode
      exports.isConcurrentMode = isConcurrentMode
      exports.isContextConsumer = isContextConsumer
      exports.isContextProvider = isContextProvider
      exports.isElement = isElement
      exports.isForwardRef = isForwardRef
      exports.isFragment = isFragment
      exports.isLazy = isLazy
      exports.isMemo = isMemo
      exports.isPortal = isPortal
      exports.isProfiler = isProfiler
      exports.isStrictMode = isStrictMode
      exports.isSuspense = isSuspense
      exports.isValidElementType = isValidElementType
      exports.typeOf = typeOf
    })()
  }
})

var reactIs = createCommonjsModule(function (module) {
  if (process.env.NODE_ENV === 'production') {
    module.exports = reactIs_production_min
  } else {
    module.exports = reactIs_development
  }
})

/*
object-assign
(c) Sindre Sorhus
@license MIT
*/
/* eslint-disable no-unused-vars */
var getOwnPropertySymbols = Object.getOwnPropertySymbols
var hasOwnProperty = Object.prototype.hasOwnProperty
var propIsEnumerable = Object.prototype.propertyIsEnumerable

function toObject(val) {
  if (val === null || val === undefined) {
    throw new TypeError('Object.assign cannot be called with null or undefined')
  }

  return Object(val)
}

function shouldUseNative() {
  try {
    if (!Object.assign) {
      return false
    }

    // Detect buggy property enumeration order in older V8 versions.

    // https://bugs.chromium.org/p/v8/issues/detail?id=4118
    var test1 = new String('abc') // eslint-disable-line no-new-wrappers
    test1[5] = 'de'
    if (Object.getOwnPropertyNames(test1)[0] === '5') {
      return false
    }

    // https://bugs.chromium.org/p/v8/issues/detail?id=3056
    var test2 = {}
    for (var i = 0; i < 10; i++) {
      test2['_' + String.fromCharCode(i)] = i
    }
    var order2 = Object.getOwnPropertyNames(test2).map(function (n) {
      return test2[n]
    })
    if (order2.join('') !== '0123456789') {
      return false
    }

    // https://bugs.chromium.org/p/v8/issues/detail?id=3056
    var test3 = {}
    'abcdefghijklmnopqrst'.split('').forEach(function (letter) {
      test3[letter] = letter
    })
    if (
      Object.keys(Object.assign({}, test3)).join('') !== 'abcdefghijklmnopqrst'
    ) {
      return false
    }

    return true
  } catch (err) {
    // We don't expect any of the above to throw, but better to be safe.
    return false
  }
}

var objectAssign = shouldUseNative()
  ? Object.assign
  : function (target, source) {
      var from
      var to = toObject(target)
      var symbols

      for (var s = 1; s < arguments.length; s++) {
        from = Object(arguments[s])

        for (var key in from) {
          if (hasOwnProperty.call(from, key)) {
            to[key] = from[key]
          }
        }

        if (getOwnPropertySymbols) {
          symbols = getOwnPropertySymbols(from)
          for (var i = 0; i < symbols.length; i++) {
            if (propIsEnumerable.call(from, symbols[i])) {
              to[symbols[i]] = from[symbols[i]]
            }
          }
        }
      }

      return to
    }

/**
 * Copyright (c) 2013-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

var ReactPropTypesSecret = 'SECRET_DO_NOT_PASS_THIS_OR_YOU_WILL_BE_FIRED'

var ReactPropTypesSecret_1 = ReactPropTypesSecret

var printWarning = function () {}

if (process.env.NODE_ENV !== 'production') {
  var ReactPropTypesSecret$1 = ReactPropTypesSecret_1
  var loggedTypeFailures = {}
  var has = Function.call.bind(Object.prototype.hasOwnProperty)

  printWarning = function (text) {
    var message = 'Warning: ' + text
    if (typeof console !== 'undefined') {
      console.error(message)
    }
    try {
      // --- Welcome to debugging React ---
      // This error was thrown as a convenience so that you can use this stack
      // to find the callsite that caused this warning to fire.
      throw new Error(message)
    } catch (x) {}
  }
}

/**
 * Assert that the values match with the type specs.
 * Error messages are memorized and will only be shown once.
 *
 * @param {object} typeSpecs Map of name to a ReactPropType
 * @param {object} values Runtime values that need to be type-checked
 * @param {string} location e.g. "prop", "context", "child context"
 * @param {string} componentName Name of the component for error messages.
 * @param {?Function} getStack Returns the component stack.
 * @private
 */
function checkPropTypes(typeSpecs, values, location, componentName, getStack) {
  if (process.env.NODE_ENV !== 'production') {
    for (var typeSpecName in typeSpecs) {
      if (has(typeSpecs, typeSpecName)) {
        var error
        // Prop type validation may throw. In case they do, we don't want to
        // fail the render phase where it didn't fail before. So we log it.
        // After these have been cleaned up, we'll let them throw.
        try {
          // This is intentionally an invariant that gets caught. It's the same
          // behavior as without this statement except with a better message.
          if (typeof typeSpecs[typeSpecName] !== 'function') {
            var err = Error(
              (componentName || 'React class') +
                ': ' +
                location +
                ' type `' +
                typeSpecName +
                '` is invalid; ' +
                'it must be a function, usually from the `prop-types` package, but received `' +
                typeof typeSpecs[typeSpecName] +
                '`.',
            )
            err.name = 'Invariant Violation'
            throw err
          }
          error = typeSpecs[typeSpecName](
            values,
            typeSpecName,
            componentName,
            location,
            null,
            ReactPropTypesSecret$1,
          )
        } catch (ex) {
          error = ex
        }
        if (error && !(error instanceof Error)) {
          printWarning(
            (componentName || 'React class') +
              ': type specification of ' +
              location +
              ' `' +
              typeSpecName +
              '` is invalid; the type checker ' +
              'function must return `null` or an `Error` but returned a ' +
              typeof error +
              '. ' +
              'You may have forgotten to pass an argument to the type checker ' +
              'creator (arrayOf, instanceOf, objectOf, oneOf, oneOfType, and ' +
              'shape all require an argument).',
          )
        }
        if (error instanceof Error && !(error.message in loggedTypeFailures)) {
          // Only monitor this failure once because there tends to be a lot of the
          // same error.
          loggedTypeFailures[error.message] = true

          var stack = getStack ? getStack() : ''

          printWarning(
            'Failed ' +
              location +
              ' type: ' +
              error.message +
              (stack != null ? stack : ''),
          )
        }
      }
    }
  }
}

/**
 * Resets warning cache when testing.
 *
 * @private
 */
checkPropTypes.resetWarningCache = function () {
  if (process.env.NODE_ENV !== 'production') {
    loggedTypeFailures = {}
  }
}

var checkPropTypes_1 = checkPropTypes

var has$1 = Function.call.bind(Object.prototype.hasOwnProperty)
var printWarning$1 = function () {}

if (process.env.NODE_ENV !== 'production') {
  printWarning$1 = function (text) {
    var message = 'Warning: ' + text
    if (typeof console !== 'undefined') {
      console.error(message)
    }
    try {
      // --- Welcome to debugging React ---
      // This error was thrown as a convenience so that you can use this stack
      // to find the callsite that caused this warning to fire.
      throw new Error(message)
    } catch (x) {}
  }
}

function emptyFunctionThatReturnsNull() {
  return null
}

var factoryWithTypeCheckers = function (isValidElement, throwOnDirectAccess) {
  /* global Symbol */
  var ITERATOR_SYMBOL = typeof Symbol === 'function' && Symbol.iterator
  var FAUX_ITERATOR_SYMBOL = '@@iterator' // Before Symbol spec.

  /**
   * Returns the iterator method function contained on the iterable object.
   *
   * Be sure to invoke the function with the iterable as context:
   *
   *     var iteratorFn = getIteratorFn(myIterable);
   *     if (iteratorFn) {
   *       var iterator = iteratorFn.call(myIterable);
   *       ...
   *     }
   *
   * @param {?object} maybeIterable
   * @return {?function}
   */
  function getIteratorFn(maybeIterable) {
    var iteratorFn =
      maybeIterable &&
      ((ITERATOR_SYMBOL && maybeIterable[ITERATOR_SYMBOL]) ||
        maybeIterable[FAUX_ITERATOR_SYMBOL])
    if (typeof iteratorFn === 'function') {
      return iteratorFn
    }
  }

  /**
   * Collection of methods that allow declaration and validation of props that are
   * supplied to React components. Example usage:
   *
   *   var Props = require('ReactPropTypes');
   *   var MyArticle = React.createClass({
   *     propTypes: {
   *       // An optional string prop named "description".
   *       description: Props.string,
   *
   *       // A required enum prop named "category".
   *       category: Props.oneOf(['News','Photos']).isRequired,
   *
   *       // A prop named "dialog" that requires an instance of Dialog.
   *       dialog: Props.instanceOf(Dialog).isRequired
   *     },
   *     render: function() { ... }
   *   });
   *
   * A more formal specification of how these methods are used:
   *
   *   type := array|bool|func|object|number|string|oneOf([...])|instanceOf(...)
   *   decl := ReactPropTypes.{type}(.isRequired)?
   *
   * Each and every declaration produces a function with the same signature. This
   * allows the creation of custom validation functions. For example:
   *
   *  var MyLink = React.createClass({
   *    propTypes: {
   *      // An optional string or URI prop named "href".
   *      href: function(props, propName, componentName) {
   *        var propValue = props[propName];
   *        if (propValue != null && typeof propValue !== 'string' &&
   *            !(propValue instanceof URI)) {
   *          return new Error(
   *            'Expected a string or an URI for ' + propName + ' in ' +
   *            componentName
   *          );
   *        }
   *      }
   *    },
   *    render: function() {...}
   *  });
   *
   * @internal
   */

  var ANONYMOUS = '<<anonymous>>'

  // Important!
  // Keep this list in sync with production version in `./factoryWithThrowingShims.js`.
  var ReactPropTypes = {
    array: createPrimitiveTypeChecker('array'),
    bool: createPrimitiveTypeChecker('boolean'),
    func: createPrimitiveTypeChecker('function'),
    number: createPrimitiveTypeChecker('number'),
    object: createPrimitiveTypeChecker('object'),
    string: createPrimitiveTypeChecker('string'),
    symbol: createPrimitiveTypeChecker('symbol'),

    any: createAnyTypeChecker(),
    arrayOf: createArrayOfTypeChecker,
    element: createElementTypeChecker(),
    elementType: createElementTypeTypeChecker(),
    instanceOf: createInstanceTypeChecker,
    node: createNodeChecker(),
    objectOf: createObjectOfTypeChecker,
    oneOf: createEnumTypeChecker,
    oneOfType: createUnionTypeChecker,
    shape: createShapeTypeChecker,
    exact: createStrictShapeTypeChecker,
  }

  /**
   * inlined Object.is polyfill to avoid requiring consumers ship their own
   * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/is
   */
  /*eslint-disable no-self-compare*/
  function is(x, y) {
    // SameValue algorithm
    if (x === y) {
      // Steps 1-5, 7-10
      // Steps 6.b-6.e: +0 != -0
      return x !== 0 || 1 / x === 1 / y
    } else {
      // Step 6.a: NaN == NaN
      return x !== x && y !== y
    }
  }
  /*eslint-enable no-self-compare*/

  /**
   * We use an Error-like object for backward compatibility as people may call
   * PropTypes directly and inspect their output. However, we don't use real
   * Errors anymore. We don't inspect their stack anyway, and creating them
   * is prohibitively expensive if they are created too often, such as what
   * happens in oneOfType() for any type before the one that matched.
   */
  function PropTypeError(message) {
    this.message = message
    this.stack = ''
  }
  // Make `instanceof Error` still work for returned errors.
  PropTypeError.prototype = Error.prototype

  function createChainableTypeChecker(validate) {
    if (process.env.NODE_ENV !== 'production') {
      var manualPropTypeCallCache = {}
      var manualPropTypeWarningCount = 0
    }
    function checkType(
      isRequired,
      props,
      propName,
      componentName,
      location,
      propFullName,
      secret,
    ) {
      componentName = componentName || ANONYMOUS
      propFullName = propFullName || propName

      if (secret !== ReactPropTypesSecret_1) {
        if (throwOnDirectAccess) {
          // New behavior only for users of `prop-types` package
          var err = new Error(
            'Calling PropTypes validators directly is not supported by the `prop-types` package. ' +
              'Use `PropTypes.checkPropTypes()` to call them. ' +
              'Read more at http://fb.me/use-check-prop-types',
          )
          err.name = 'Invariant Violation'
          throw err
        } else if (
          process.env.NODE_ENV !== 'production' &&
          typeof console !== 'undefined'
        ) {
          // Old behavior for people using React.PropTypes
          var cacheKey = componentName + ':' + propName
          if (
            !manualPropTypeCallCache[cacheKey] &&
            // Avoid spamming the console because they are often not actionable except for lib authors
            manualPropTypeWarningCount < 3
          ) {
            printWarning$1(
              'You are manually calling a React.PropTypes validation ' +
                'function for the `' +
                propFullName +
                '` prop on `' +
                componentName +
                '`. This is deprecated ' +
                'and will throw in the standalone `prop-types` package. ' +
                'You may be seeing this warning due to a third-party PropTypes ' +
                'library. See https://fb.me/react-warning-dont-call-proptypes ' +
                'for details.',
            )
            manualPropTypeCallCache[cacheKey] = true
            manualPropTypeWarningCount++
          }
        }
      }
      if (props[propName] == null) {
        if (isRequired) {
          if (props[propName] === null) {
            return new PropTypeError(
              'The ' +
                location +
                ' `' +
                propFullName +
                '` is marked as required ' +
                ('in `' + componentName + '`, but its value is `null`.'),
            )
          }
          return new PropTypeError(
            'The ' +
              location +
              ' `' +
              propFullName +
              '` is marked as required in ' +
              ('`' + componentName + '`, but its value is `undefined`.'),
          )
        }
        return null
      } else {
        return validate(props, propName, componentName, location, propFullName)
      }
    }

    var chainedCheckType = checkType.bind(null, false)
    chainedCheckType.isRequired = checkType.bind(null, true)

    return chainedCheckType
  }

  function createPrimitiveTypeChecker(expectedType) {
    function validate(
      props,
      propName,
      componentName,
      location,
      propFullName,
      secret,
    ) {
      var propValue = props[propName]
      var propType = getPropType(propValue)
      if (propType !== expectedType) {
        // `propValue` being instance of, say, date/regexp, pass the 'object'
        // check, but we can offer a more precise error message here rather than
        // 'of type `object`'.
        var preciseType = getPreciseType(propValue)

        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type ' +
            ('`' +
              preciseType +
              '` supplied to `' +
              componentName +
              '`, expected ') +
            ('`' + expectedType + '`.'),
        )
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createAnyTypeChecker() {
    return createChainableTypeChecker(emptyFunctionThatReturnsNull)
  }

  function createArrayOfTypeChecker(typeChecker) {
    function validate(props, propName, componentName, location, propFullName) {
      if (typeof typeChecker !== 'function') {
        return new PropTypeError(
          'Property `' +
            propFullName +
            '` of component `' +
            componentName +
            '` has invalid PropType notation inside arrayOf.',
        )
      }
      var propValue = props[propName]
      if (!Array.isArray(propValue)) {
        var propType = getPropType(propValue)
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type ' +
            ('`' +
              propType +
              '` supplied to `' +
              componentName +
              '`, expected an array.'),
        )
      }
      for (var i = 0; i < propValue.length; i++) {
        var error = typeChecker(
          propValue,
          i,
          componentName,
          location,
          propFullName + '[' + i + ']',
          ReactPropTypesSecret_1,
        )
        if (error instanceof Error) {
          return error
        }
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createElementTypeChecker() {
    function validate(props, propName, componentName, location, propFullName) {
      var propValue = props[propName]
      if (!isValidElement(propValue)) {
        var propType = getPropType(propValue)
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type ' +
            ('`' +
              propType +
              '` supplied to `' +
              componentName +
              '`, expected a single ReactElement.'),
        )
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createElementTypeTypeChecker() {
    function validate(props, propName, componentName, location, propFullName) {
      var propValue = props[propName]
      if (!reactIs.isValidElementType(propValue)) {
        var propType = getPropType(propValue)
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type ' +
            ('`' +
              propType +
              '` supplied to `' +
              componentName +
              '`, expected a single ReactElement type.'),
        )
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createInstanceTypeChecker(expectedClass) {
    function validate(props, propName, componentName, location, propFullName) {
      if (!(props[propName] instanceof expectedClass)) {
        var expectedClassName = expectedClass.name || ANONYMOUS
        var actualClassName = getClassName(props[propName])
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type ' +
            ('`' +
              actualClassName +
              '` supplied to `' +
              componentName +
              '`, expected ') +
            ('instance of `' + expectedClassName + '`.'),
        )
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createEnumTypeChecker(expectedValues) {
    if (!Array.isArray(expectedValues)) {
      if (process.env.NODE_ENV !== 'production') {
        if (arguments.length > 1) {
          printWarning$1(
            'Invalid arguments supplied to oneOf, expected an array, got ' +
              arguments.length +
              ' arguments. ' +
              'A common mistake is to write oneOf(x, y, z) instead of oneOf([x, y, z]).',
          )
        } else {
          printWarning$1(
            'Invalid argument supplied to oneOf, expected an array.',
          )
        }
      }
      return emptyFunctionThatReturnsNull
    }

    function validate(props, propName, componentName, location, propFullName) {
      var propValue = props[propName]
      for (var i = 0; i < expectedValues.length; i++) {
        if (is(propValue, expectedValues[i])) {
          return null
        }
      }

      var valuesString = JSON.stringify(
        expectedValues,
        function replacer(key, value) {
          var type = getPreciseType(value)
          if (type === 'symbol') {
            return String(value)
          }
          return value
        },
      )
      return new PropTypeError(
        'Invalid ' +
          location +
          ' `' +
          propFullName +
          '` of value `' +
          String(propValue) +
          '` ' +
          ('supplied to `' +
            componentName +
            '`, expected one of ' +
            valuesString +
            '.'),
      )
    }
    return createChainableTypeChecker(validate)
  }

  function createObjectOfTypeChecker(typeChecker) {
    function validate(props, propName, componentName, location, propFullName) {
      if (typeof typeChecker !== 'function') {
        return new PropTypeError(
          'Property `' +
            propFullName +
            '` of component `' +
            componentName +
            '` has invalid PropType notation inside objectOf.',
        )
      }
      var propValue = props[propName]
      var propType = getPropType(propValue)
      if (propType !== 'object') {
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type ' +
            ('`' +
              propType +
              '` supplied to `' +
              componentName +
              '`, expected an object.'),
        )
      }
      for (var key in propValue) {
        if (has$1(propValue, key)) {
          var error = typeChecker(
            propValue,
            key,
            componentName,
            location,
            propFullName + '.' + key,
            ReactPropTypesSecret_1,
          )
          if (error instanceof Error) {
            return error
          }
        }
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createUnionTypeChecker(arrayOfTypeCheckers) {
    if (!Array.isArray(arrayOfTypeCheckers)) {
      process.env.NODE_ENV !== 'production'
        ? printWarning$1(
            'Invalid argument supplied to oneOfType, expected an instance of array.',
          )
        : void 0
      return emptyFunctionThatReturnsNull
    }

    for (var i = 0; i < arrayOfTypeCheckers.length; i++) {
      var checker = arrayOfTypeCheckers[i]
      if (typeof checker !== 'function') {
        printWarning$1(
          'Invalid argument supplied to oneOfType. Expected an array of check functions, but ' +
            'received ' +
            getPostfixForTypeWarning(checker) +
            ' at index ' +
            i +
            '.',
        )
        return emptyFunctionThatReturnsNull
      }
    }

    function validate(props, propName, componentName, location, propFullName) {
      for (var i = 0; i < arrayOfTypeCheckers.length; i++) {
        var checker = arrayOfTypeCheckers[i]
        if (
          checker(
            props,
            propName,
            componentName,
            location,
            propFullName,
            ReactPropTypesSecret_1,
          ) == null
        ) {
          return null
        }
      }

      return new PropTypeError(
        'Invalid ' +
          location +
          ' `' +
          propFullName +
          '` supplied to ' +
          ('`' + componentName + '`.'),
      )
    }
    return createChainableTypeChecker(validate)
  }

  function createNodeChecker() {
    function validate(props, propName, componentName, location, propFullName) {
      if (!isNode(props[propName])) {
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` supplied to ' +
            ('`' + componentName + '`, expected a ReactNode.'),
        )
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createShapeTypeChecker(shapeTypes) {
    function validate(props, propName, componentName, location, propFullName) {
      var propValue = props[propName]
      var propType = getPropType(propValue)
      if (propType !== 'object') {
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type `' +
            propType +
            '` ' +
            ('supplied to `' + componentName + '`, expected `object`.'),
        )
      }
      for (var key in shapeTypes) {
        var checker = shapeTypes[key]
        if (!checker) {
          continue
        }
        var error = checker(
          propValue,
          key,
          componentName,
          location,
          propFullName + '.' + key,
          ReactPropTypesSecret_1,
        )
        if (error) {
          return error
        }
      }
      return null
    }
    return createChainableTypeChecker(validate)
  }

  function createStrictShapeTypeChecker(shapeTypes) {
    function validate(props, propName, componentName, location, propFullName) {
      var propValue = props[propName]
      var propType = getPropType(propValue)
      if (propType !== 'object') {
        return new PropTypeError(
          'Invalid ' +
            location +
            ' `' +
            propFullName +
            '` of type `' +
            propType +
            '` ' +
            ('supplied to `' + componentName + '`, expected `object`.'),
        )
      }
      // We need to check all keys in case some are required but missing from
      // props.
      var allKeys = objectAssign({}, props[propName], shapeTypes)
      for (var key in allKeys) {
        var checker = shapeTypes[key]
        if (!checker) {
          return new PropTypeError(
            'Invalid ' +
              location +
              ' `' +
              propFullName +
              '` key `' +
              key +
              '` supplied to `' +
              componentName +
              '`.' +
              '\nBad object: ' +
              JSON.stringify(props[propName], null, '  ') +
              '\nValid keys: ' +
              JSON.stringify(Object.keys(shapeTypes), null, '  '),
          )
        }
        var error = checker(
          propValue,
          key,
          componentName,
          location,
          propFullName + '.' + key,
          ReactPropTypesSecret_1,
        )
        if (error) {
          return error
        }
      }
      return null
    }

    return createChainableTypeChecker(validate)
  }

  function isNode(propValue) {
    switch (typeof propValue) {
      case 'number':
      case 'string':
      case 'undefined':
        return true
      case 'boolean':
        return !propValue
      case 'object':
        if (Array.isArray(propValue)) {
          return propValue.every(isNode)
        }
        if (propValue === null || isValidElement(propValue)) {
          return true
        }

        var iteratorFn = getIteratorFn(propValue)
        if (iteratorFn) {
          var iterator = iteratorFn.call(propValue)
          var step
          if (iteratorFn !== propValue.entries) {
            while (!(step = iterator.next()).done) {
              if (!isNode(step.value)) {
                return false
              }
            }
          } else {
            // Iterator will provide entry [k,v] tuples rather than values.
            while (!(step = iterator.next()).done) {
              var entry = step.value
              if (entry) {
                if (!isNode(entry[1])) {
                  return false
                }
              }
            }
          }
        } else {
          return false
        }

        return true
      default:
        return false
    }
  }

  function isSymbol(propType, propValue) {
    // Native Symbol.
    if (propType === 'symbol') {
      return true
    }

    // falsy value can't be a Symbol
    if (!propValue) {
      return false
    }

    // 19.4.3.5 Symbol.prototype[@@toStringTag] === 'Symbol'
    if (propValue['@@toStringTag'] === 'Symbol') {
      return true
    }

    // Fallback for non-spec compliant Symbols which are polyfilled.
    if (typeof Symbol === 'function' && propValue instanceof Symbol) {
      return true
    }

    return false
  }

  // Equivalent of `typeof` but with special handling for array and regexp.
  function getPropType(propValue) {
    var propType = typeof propValue
    if (Array.isArray(propValue)) {
      return 'array'
    }
    if (propValue instanceof RegExp) {
      // Old webkits (at least until Android 4.0) return 'function' rather than
      // 'object' for typeof a RegExp. We'll normalize this here so that /bla/
      // passes PropTypes.object.
      return 'object'
    }
    if (isSymbol(propType, propValue)) {
      return 'symbol'
    }
    return propType
  }

  // This handles more types than `getPropType`. Only used for error messages.
  // See `createPrimitiveTypeChecker`.
  function getPreciseType(propValue) {
    if (typeof propValue === 'undefined' || propValue === null) {
      return '' + propValue
    }
    var propType = getPropType(propValue)
    if (propType === 'object') {
      if (propValue instanceof Date) {
        return 'date'
      } else if (propValue instanceof RegExp) {
        return 'regexp'
      }
    }
    return propType
  }

  // Returns a string that is postfixed to a warning about an invalid type.
  // For example, "undefined" or "of type array"
  function getPostfixForTypeWarning(value) {
    var type = getPreciseType(value)
    switch (type) {
      case 'array':
      case 'object':
        return 'an ' + type
      case 'boolean':
      case 'date':
      case 'regexp':
        return 'a ' + type
      default:
        return type
    }
  }

  // Returns class name of the object, if any.
  function getClassName(propValue) {
    if (!propValue.constructor || !propValue.constructor.name) {
      return ANONYMOUS
    }
    return propValue.constructor.name
  }

  ReactPropTypes.checkPropTypes = checkPropTypes_1
  ReactPropTypes.resetWarningCache = checkPropTypes_1.resetWarningCache
  ReactPropTypes.PropTypes = ReactPropTypes

  return ReactPropTypes
}

function emptyFunction() {}
function emptyFunctionWithReset() {}
emptyFunctionWithReset.resetWarningCache = emptyFunction

var factoryWithThrowingShims = function () {
  function shim(
    props,
    propName,
    componentName,
    location,
    propFullName,
    secret,
  ) {
    if (secret === ReactPropTypesSecret_1) {
      // It is still safe when called from React.
      return
    }
    var err = new Error(
      'Calling PropTypes validators directly is not supported by the `prop-types` package. ' +
        'Use PropTypes.checkPropTypes() to call them. ' +
        'Read more at http://fb.me/use-check-prop-types',
    )
    err.name = 'Invariant Violation'
    throw err
  }
  shim.isRequired = shim
  function getShim() {
    return shim
  } // Important!
  // Keep this list in sync with production version in `./factoryWithTypeCheckers.js`.
  var ReactPropTypes = {
    array: shim,
    bool: shim,
    func: shim,
    number: shim,
    object: shim,
    string: shim,
    symbol: shim,

    any: shim,
    arrayOf: getShim,
    element: shim,
    elementType: shim,
    instanceOf: getShim,
    node: shim,
    objectOf: getShim,
    oneOf: getShim,
    oneOfType: getShim,
    shape: getShim,
    exact: getShim,

    checkPropTypes: emptyFunctionWithReset,
    resetWarningCache: emptyFunction,
  }

  ReactPropTypes.PropTypes = ReactPropTypes

  return ReactPropTypes
}

var propTypes = createCommonjsModule(function (module) {
  /**
   * Copyright (c) 2013-present, Facebook, Inc.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */

  if (process.env.NODE_ENV !== 'production') {
    var ReactIs = reactIs

    // By explicitly using `prop-types` you are opting into new development behavior.
    // http://fb.me/prop-types-in-prod
    var throwOnDirectAccess = true
    module.exports = factoryWithTypeCheckers(
      ReactIs.isElement,
      throwOnDirectAccess,
    )
  } else {
    // By explicitly using `prop-types` you are opting into new production behavior.
    // http://fb.me/prop-types-in-prod
    module.exports = factoryWithThrowingShims()
  }
})

var Table = (function () {
  var Table = /*#__PURE__*/ (function (_React$Component) {
    _inheritsLoose(Table, _React$Component)

    function Table(props) {
      var _this

      _this = _React$Component.call(this, props) || this

      _this.backButtonOnclick = function (event) {
        if (!_this.state.back_button_clickable) {
          return
        }

        var nexbutton = _this.state.active_page_number - 1

        _this.setPage(nexbutton)
      }

      _this.searchChange = function (event) {
        var original_value = event.target.value
        var trimmed_value = original_value.replace(
          /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,
          '',
        )

        if (trimmed_value) {
          if (!_this.search_debounce) {
            _this.search_debounce = utils.debounce(
              _this.processFilter,
              _this.props.debounce_search,
            )
          }

          _this.search_debounce(
            utils.filterFunction(original_value, _this.props.columns),
          )
        } else {
          if (
            _this.state.pagination.all_rows.length !== _this.props.rows.length
          ) {
            _this.setComponentState(
              Table.setInitialPage(_this.props.rows, _this.props.per_page),
            )
          }
        }
      }

      _this.processFilter = function (filter_function) {
        var search_promise = new Promise(function (resolve) {
          resolve(
            _this.props.rows.length > 0
              ? _this.props.rows.filter(filter_function)
              : _this.props.rows,
          )
        })
        search_promise.then(function (result) {
          _this.setComponentState(
            Table.setInitialPage(result, _this.props.per_page),
          )
        })
      }

      _this.forwardButtonOnclick = function (event) {
        if (!_this.state.forward_button_clickable) {
          return
        }

        var nexbutton = _this.state.active_page_number + 1

        _this.setPage(nexbutton)
      }

      _this.pageNumberClick = function (event, page_number) {
        if (_this.state.active_page_number === page_number) {
          return
        }

        _this.setPage(page_number)
      }

      var page_set_up = Table.setInitialPage(props.rows, props.per_page)
      _this.state = {
        pagination: page_set_up.table_links,
        forward_button_clickable: page_set_up.forward_button_clickable,
        back_button_clickable: page_set_up.back_button_clickable,
        current_rows: page_set_up.current_rows,
        active_page_number: page_set_up.active_page_number,
        search_string: '',
      }
      page_set_up = null
      return _this
    }

    var _proto = Table.prototype

    _proto.UNSAFE_componentWillReceiveProps = function UNSAFE_componentWillReceiveProps(
      nextProps,
    ) {
      if (nextProps.per_page && nextProps.per_page !== this.props.per_page) {
        this.setComponentState(
          Table.setInitialPage(nextProps.rows, nextProps.per_page),
        )
        return
      }

      if (!utils.isObjectEqual(nextProps.rows, this.props.rows)) {
        var per_page = nextProps.per_page
          ? nextProps.per_page
          : this.props.per_page
        this.setComponentState(Table.setInitialPage(nextProps.rows, per_page))
        return
      }
    }

    Table.setInitialPage = function setInitialPage(rows, per_page) {
      return Table.setPageActive(utils.TableNumberLinks(rows, per_page), 1)
    }

    _proto.setPage = function setPage(page_number) {
      var stateclone = _extends({}, this.state)

      stateclone.pagination.page_map[
        stateclone.active_page_number
      ].is_active = false
      var new_data = Table.setPageActive(stateclone.pagination, page_number)
      this.setComponentState(new_data)
      stateclone = null
      new_data = null
    }

    _proto.setComponentState = function setComponentState(data_set) {
      this.setState({
        pagination: data_set.table_links,
        forward_button_clickable: data_set.forward_button_clickable,
        back_button_clickable: data_set.back_button_clickable,
        current_rows: data_set.current_rows,
        active_page_number: data_set.active_page_number,
      })
    }

    Table.setPageActive = function setPageActive(data, page_number) {
      var verify_data = _extends({}, data)

      var back_button_clickable = false
      var forward_button_clickable = false
      var current_rows = []

      if (verify_data.page_number_store.length > 0) {
        verify_data.page_map[page_number].is_active = true
        back_button_clickable =
          verify_data.page_map[page_number].back_button_clickable
        forward_button_clickable =
          verify_data.page_map[page_number].forward_button_clickable
        current_rows = verify_data.page_map[page_number].page_row_array
      }

      return {
        table_links: verify_data,
        back_button_clickable: back_button_clickable,
        forward_button_clickable: forward_button_clickable,
        current_rows: current_rows,
        active_page_number: page_number,
      }
    }

    _proto.render = function render() {
      var props = _extends({}, this.props)

      var display_columns = props.columns.filter(function (column) {
        return column.use_in_display !== false
      })
      return React.createElement(
        'div',
        {
          className: 'pb-4 px-4 rounded-md w-full',
        },
        React.createElement(
          'div',
          {
            className: 'flex justify-between w-full pt-6 ',
          },
          React.createElement(
            'p',
            {
              className: 'ml-3',
              id: 'table-header',
            },
            props.table_header,
          ),
          React.createElement(ThreeDotsvg, null),
        ),
        props.show_search !== false &&
          React.createElement(SearchForm, {
            search_string: this.state.search_string,
            searchChange: this.searchChange,
          }),
        React.createElement(
          'div',
          {
            className: 'overflow-x-auto mt-3',
          },
          React.createElement(
            'table',
            {
              className: 'table-auto border-collapse w-full',
            },
            React.createElement(
              'thead',
              null,
              React.createElement(
                'tr',
                {
                  className:
                    'rounded-lg text-sm font-medium text-gray-700 text-left',
                  style: {
                    fontSize: '0.9674rem',
                  },
                },
                display_columns.map(function (column, index) {
                  return React.createElement(
                    'th',
                    {
                      key: index.toString(),
                      className: 'px-4 py-2',
                      style: {
                        backgroundColor: '#f8f8f8',
                      },
                    },
                    column.use,
                  )
                }),
              ),
            ),
            React.createElement(
              'tbody',
              {
                className: 'text-sm font-normal text-gray-700',
                column,
              },
              (this.state.current_rows.length > 0 &&
                this.state.current_rows.map(function (row, index) {
                  return React.createElement(TableRow, {
                    key: index.toString(),
                    row: row,
                    columns: display_columns,
                    render: props.row_render,
                  })
                })) ||
                React.createElement(
                  'tr',
                  {
                    className: 'hover:bg-gray-100 border-b border-gray-200',
                  },
                  React.createElement(
                    'td',
                    {
                      className: 'px-4 py-4 text-center',
                      colSpan: display_columns.length,
                    },
                    props.no_content_text,
                  ),
                ),
            ),
          ),
        ),
        React.createElement(
          'div',
          {
            id: 'pagination',
            className:
              'w-full flex justify-center border-t border-gray-100 pt-4 items-center',
          },
          React.createElement(
            Pagination,
            Object.assign({}, this.state, {
              backButtonOnclick: this.backButtonOnclick,
              forwardButtonOnclick: this.forwardButtonOnclick,
              pageNumberClick: this.pageNumberClick,
            }),
          ),
        ),
      )
    }

    return Table
  })(React.Component)

  Table.defaultProps = {
    no_content_text: 'No Data Availaible',
    per_page: 10,
    debounce_search: 300,
    table_header: '',
  }
  Table.propTypes = {
    rows: propTypes.array.isRequired,
    columns: propTypes.array.isRequired,
    debounce_search: propTypes.number,
    per_page: propTypes.number,
    no_content_text: propTypes.string,
    table_header: propTypes.string,
  }
  return Table
})()

function Pagination(props) {
  return React.createElement(
    'div',
    {
      id: 'pagination',
      className: 'w-full flex justify-center pt-4 items-center',
    },
    React.createElement(LeftSvg, {
      onClick: props.backButtonOnclick,
      className:
        'h-3 w-3 fill-current previous-button ' +
        (props.back_button_clickable
          ? 'cursor-pointer text-blue-500'
          : 'text-gray-700 cursor-not-allowed'),
    }),
    props.pagination.page_number_store.map(function (page_number) {
      return React.createElement(
        'p',
        {
          onClick: function onClick(event) {
            props.pageNumberClick(event, page_number)
          },
          key: page_number,
          className:
            'leading-relaxed \n                       mx-2 hover:text-blue-600 text-sm \n                       ' +
            (props.pagination.page_map[page_number].is_active
              ? 'text-blue-600 cursor-not-allowed'
              : 'text-gray-700 cursor-pointer'),
        },
        page_number,
      )
    }),
    React.createElement(RightSvg, {
      onClick: props.forwardButtonOnclick,
      className:
        'h-3 w-3 fill-current next-button ' +
        (props.forward_button_clickable
          ? 'cursor-pointer text-blue-500'
          : 'text-gray-700 cursor-not-allowed'),
    }),
  )
}

function TableRow(props) {
  return React.createElement(
    'tr',
    {
      className: 'hover:bg-gray-100 border-b border-gray-200 ',
    },
    props.columns.map(function (column, index) {
      return React.createElement(TableData, {
        key: index.toString(),
        col: column,
        row: props.row,
        render: props.render,
      })
    }),
  )
}

function SearchForm(props) {
  return React.createElement(
    'div',
    {
      className: 'w-full justify-end flex px-2 mt-2',
    },
    React.createElement(
      'div',
      {
        className: 'w-full sm:w-64 inline-block relative ', //class searg gsm
      },
      React.createElement('input', {
        onChange: props.searchChange,
        type: 'text',
        name: 'search form',
        className:
          'leading-snug border border-gray-300 block w-full appearance-none bg-gray-100 text-sm text-gray-600 py-1 px-4 pl-8 rounded-lg ',
        placeholder: 'Search',
      }),
      React.createElement(
        'div',
        {
          className:
            'pointer-events-none absolute pl-3 inset-y-0 left-0 flex items-center px-2 text-gray-300',
        },
        React.createElement(
          'svg',
          {
            className: 'fill-current h-3 w-3',
            xmlns: 'http://www.w3.org/2000/svg',
            viewBox: '0 0 511.999 511.999',
          },
          React.createElement('path', {
            d:
              'M508.874 478.708L360.142 329.976c28.21-34.827 45.191-79.103 45.191-127.309C405.333 90.917 314.416 0 202.666 0S0 90.917 0 202.667s90.917 202.667 202.667 202.667c48.206 0 92.482-16.982 127.309-45.191l148.732 148.732c4.167 4.165 10.919 4.165 15.086 0l15.081-15.082c4.165-4.166 4.165-10.92-.001-15.085zM202.667 362.667c-88.229 0-160-71.771-160-160s71.771-160 160-160 160 71.771 160 160-71.771 160-160 160z',
          }),
        ),
      ),
    ),
  )
}

function TableData(props) {
  var display_value = utils.unwindObject(props.row, props.col.field)
  return React.createElement(
    'td',
    {
      className: 'px-4 py-4',
    },
    (props.render && props.render(props.row, props.col, display_value)) ||
      display_value,
  )
}

module.exports = Table
//# sourceMappingURL=index.js.map
