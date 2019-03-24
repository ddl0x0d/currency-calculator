import axios from 'axios';
import Currencies from './Currencies';

jest.mock('axios');

describe('Currencies', () => {
  const errors = [{ message: 'error' }];
  const errMsgResponse = { message: 'error' };
  const errDataResponse = { response: { data: { errors } } };

  describe('get', () => {
    it('succeeds', async () => {
      const currencies = ['EUR', 'GBP'];
      const response = { data: { data: currencies } };
      axios.get.mockName('axios.get').mockResolvedValue(response);

      const result = await Currencies.get();

      expect(result).toEqual(currencies);
      expect(axios.get).toBeCalledWith('/api/currencies');
    });

    it('fails with response data errors', async () => {
      expect.assertions(1);
      axios.get.mockName('axios.get').mockRejectedValue(errDataResponse);

      await expect(Currencies.get()).rejects.toEqual(errors);
    });

    it('fails with error message', async () => {
      expect.assertions(1);
      axios.get.mockName('axios.get').mockRejectedValue(errMsgResponse);

      await expect(Currencies.get()).rejects.toEqual(errors);
    });
  });

  describe('convert', () => {
    const request = { amount: 100, from: 'EUR', to: 'GBP' };

    it('succeeds', async () => {
      const response = { data: { data: 85 } };
      axios.post.mockName('axios.port').mockResolvedValue(response);

      const result = await Currencies.convert(request);

      expect(result).toBe('85.00');
      expect(axios.post).toBeCalledWith('/api/currencies/convert', request);
    });

    it('fails with response data errors', async () => {
      expect.assertions(1);
      axios.post.mockName('axios.post').mockRejectedValue(errDataResponse);

      await expect(Currencies.convert(request)).rejects.toEqual(errors);
    });

    it('fails with error message', async () => {
      expect.assertions(1);
      axios.post.mockName('axios.post').mockRejectedValue(errMsgResponse);

      await expect(Currencies.convert(request)).rejects.toEqual(errors);
    });
  });
});
