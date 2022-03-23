import React from 'react'

import './PhotoMessage.css'

import { FaCloudDownloadAlt } from 'react-icons/fa'
import { MdError } from 'react-icons/md'

const ProgressBar = require('react-progress-bar.js')
const Circle = ProgressBar.Circle

export class PhotoMessage extends React.PureComponent {
  render() {
    var progressOptions = {
      strokeWidth: 2.3,
      color: '#efe',
      trailColor: '#aaa',
      trailWidth: 1,
      step: (state, circle) => {
        circle.path.setAttribute('trail', state.color)
        circle.path.setAttribute('trailwidth-width', state.width)

        var value = Math.round(circle.value() * 100)
        if (value === 0) circle.setText('')
        else circle.setText(value)
      },
    }

    const error =
      this.props.data.status && this.props.data.status.error === true

    return (
      <div className="rce-mbox-photo">
        <div
          className="rce-mbox-photo--img"
          style={
            this.props.data.width &&
            this.props.data.height && {
              width: this.props.data.width,
              height: this.props.data.height,
            }
          }
        >
          <img
            src={this.props.data.uri}
            alt={this.props.data.alt}
            onClick={this.props.onOpen}
            onLoad={this.props.onLoad}
            onError={this.props.onPhotoError}
          />
          {error && (
            <div className="rce-mbox-photo--img__block">
              <span className="rce-mbox-photo--img__block-item rce-mbox-photo--error">
                <MdError />
              </span>
            </div>
          )}
          {!error &&
            this.props.data.status &&
            !this.props.data.status.download && (
              <div className="rce-mbox-photo--img__block">
                {!this.props.data.status.click && (
                  <button
                    onClick={this.props.onDownload}
                    className="rce-mbox-photo--img__block-item rce-mbox-photo--download"
                  >
                    <FaCloudDownloadAlt />
                  </button>
                )}
                {typeof this.props.data.status.loading === 'number' &&
                  this.props.data.status.loading !== 0 && (
                    <Circle
                      progress={this.props.data.status.loading}
                      options={progressOptions}
                      initialAnimate={true}
                      containerClassName={'rce-mbox-photo--img__block-item'}
                    />
                  )}
              </div>
            )}
        </div>
        {this.props.text && (
          <div className="rce-mbox-text">{this.props.text}</div>
        )}
      </div>
    )
  }
}

PhotoMessage.defaultProps = {
  text: '',
  data: {},
  onDownload: null,
  onOpen: null,
  onLoad: null,
  onPhotoError: null,
}

export default PhotoMessage
