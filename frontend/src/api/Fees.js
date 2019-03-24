import axios from 'axios';
import { extractMessages } from './errorService';

const api = '/api/fees';

export const get = async () => {
  try {
    const response = await axios.get(api);
    return response.data.data;
  } catch (e) {
    throw extractMessages(e);
  }
};

export const add = async fee => {
  try {
    const response = await axios.post(api, fee);
    return response.data.data;
  } catch (e) {
    throw extractMessages(e);
  }
};

export const remove = async id => {
  try {
    return await axios.delete(`${api}/${id}`);
  } catch (e) {
    if (e.response && e.response.status === 404) {
      console.warn(`Fee with ID "${id}" not found`);
    } else {
      throw extractMessages(e);
    }
  }
};

export default { get, add, remove };
