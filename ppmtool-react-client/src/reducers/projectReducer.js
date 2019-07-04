import { GET_PROJECTS } from '../actions/Types'

const initialState = {
  projects: [],
  project: {}
}

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload
      }
    default:
      return state
  }
}
