import classNames from 'classnames'
import React, { useEffect } from 'react'
import $ from 'jquery'

const MultipleFile = ({ multipleImages, setMultipleImages, register, errors }) => {
    useEffect(() => {
        $('.inputfile-box input[type="file"]').on('change', function () {
            var infile = $(this).val();
            var filename = infile.split("\\");
            filename = filename[filename.length - 1];
            var multImg = [...multipleImages]
            var Joined = multImg.concat(Array.from($(this)[0].files));
            setMultipleImages(Joined)
        });
    }, [multipleImages])

    const deleteFile = (ind) => {
        const cloneMulImg = [...multipleImages]
        cloneMulImg.splice(ind, 1)
        setMultipleImages(cloneMulImg)
    }
    return (
        <div>
            <div class="inputfile-box">
                <label for="file">
                    <span id="reporter_information_file-name" class="file-box">Upload Document <b>*</b></span>
                    <span class="file-button">
                        Select File
                    </span>
                </label>
                <input type="file"
                    id="file"
                    multiple={true}
                    className={classNames('inputfile', {
                        "is-invalid": errors.file,
                    })}
                    {...register("file", { required: "Document is Required." })}
                />{errors.file && (
                    <div className="invalid-feedback">
                        {errors.file.message}
                    </div>
                )}
            </div>
            <table className="driverresults">
                <tbody>
                    {multipleImages.map((val, ind) => {
                        return (
                            <tr>
                                <td>{ind + 1}</td>
                                <td>{val.name}</td>
                                <td>
                                    &ensp;
                                    <span type='button' onClick={() => { deleteFile(ind) }}>&#10005;</span>
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default MultipleFile