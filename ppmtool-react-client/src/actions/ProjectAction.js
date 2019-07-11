import axios from 'axios'
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT } from './Types'

export const createProject = (project, history) => async dispatch => {
  try {
    // console.log(project)
    const res = await axios.post('http://localhost:8080/api/project', project)
    // console.log(res)
    history.push('/dashboard')
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    })
  }
}

export const getProjects = () => async dispatch => {
  const res = await axios.get('http://localhost:8080/api/project/all')
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  })
}

export const getProject = (id, history) => async dispatch => {
  const res = await axios.get(`http://localhost:8080/api/project/${id}`)
  dispatch({
    type: GET_PROJECT,
    payload: res.data
  })
}
