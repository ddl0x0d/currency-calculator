import axios from 'axios';
import { extractMessages } from './errorService';

const api = '/api/currencies';

export const get = async () => {
  try {
    const response = await axios.get(`${api}`);
    return response.data.data;
  } catch (e) {
    throw extractMessages(e);
  }
};

export const convert = async request => {
  try {
    const response = await axios.post(`${api}/convert`, request);
    return response.data.data.toFixed(2);
  } catch (e) {
    throw extractMessages(e);
  }
};

export default { get, convert };
