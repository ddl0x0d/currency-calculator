import axios from 'axios';
import Fees from './Fees';

jest.mock('axios');

describe('Fees', () => {
  const errors = [{ message: 'error' }];
  const errMsgResponse = { message: 'error' };
  const errDataResponse = { response: { data: { errors } } };

  describe('get', () => {
    it('succeeds', async () => {
      const fees = [{ id: '123', from: 'EUR', to: 'GBP', amount: 0.01 }];
      const response = { data: { data: fees } };
      axios.get.mockName('axios.get').mockResolvedValue(response);

      const result = await Fees.get();

      expect(result).toEqual(fees);
      expect(axios.get).toHaveBeenCalledWith('/api/fees');
    });

    it('fails with response data errors', async () => {
      expect.assertions(1);
      axios.get.mockName('axios.get').mockRejectedValue(errDataResponse);

      await expect(Fees.get()).rejects.toEqual(errors);
    });

    it('fails with error message', () => {
      expect.assertions(1);
      axios.get.mockName('axios.get').mockRejectedValue(errMsgResponse);

      return expect(Fees.get()).rejects.toEqual(errors);
    });
  });

  describe('add', () => {
    const feeRequest = { from: 'EUR', to: 'GBP', amount: 0.01 };

    it('succeeds', async () => {
      const feeResponse = { id: '123', ...feeRequest };
      const response = { data: { data: feeResponse } };
      axios.post.mockName('axios.post').mockResolvedValue(response);

      const result = await Fees.add(feeRequest);

      expect(result).toEqual(feeResponse);
      expect(axios.post).toHaveBeenCalledWith('/api/fees', feeRequest);
    });

    it('fails with response data errors', async () => {
      expect.assertions(1);
      axios.post.mockName('axios.post').mockRejectedValue(errDataResponse);

      await expect(Fees.add(feeRequest)).rejects.toEqual(errors);
    });

    it('fails with error message', async () => {
      expect.assertions(1);
      axios.post.mockName('axios.post').mockRejectedValue(errMsgResponse);

      await expect(Fees.add(feeRequest)).rejects.toEqual(errors);
    });
  });

  describe('remove', () => {
    const id = '123';

    it('succeeds', async () => {
      const response = { data: null };

      axios.delete.mockName('axios.remove').mockResolvedValue(response);

      const result = await Fees.remove(id);

      expect(result).toEqual(response);
      expect(axios.delete).toHaveBeenCalledWith('/api/fees/123');
    });

    it('not found', async () => {
      expect.assertions(1);
      const response = { response: { status: 404 } };
      axios.delete.mockName('axios.remove').mockRejectedValue(response);

      await expect(Fees.remove(id)).resolves.toBeUndefined();
    });

    it('fails with response data errors', async () => {
      expect.assertions(1);
      axios.delete.mockName('axios.remove').mockRejectedValue(errDataResponse);

      await expect(Fees.remove(id)).rejects.toEqual(errors);
    });

    it('fails with error message', async () => {
      expect.assertions(1);
      axios.delete.mockName('axios.remove').mockRejectedValue(errMsgResponse);

      await expect(Fees.remove(id)).rejects.toEqual(errors);
    });
  });
});
