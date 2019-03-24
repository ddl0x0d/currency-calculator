import AppContext from '../../AppContext';
import Calculator from './Calculator';
import Currencies from '../../api/Currencies';
import { mount, shallow } from 'enzyme';
import React from 'react';

jest.mock('../../api/Currencies');

describe('<Calculator />', () => {
  it('renders correctly', () => {
    const wrapper = shallow(<Calculator />);
    expect(wrapper).toMatchSnapshot();
  });

  describe('convert currency', () => {
    beforeEach(() => {
      jest.clearAllMocks();
    });

    const showErrors = jest.fn().mockName('showErrors');
    const wrapper = mount(
      <AppContext.Provider value={{ currencies: [], showErrors }}>
        <Calculator />
      </AppContext.Provider>
    );

    const change = (selector, value) => {
      const element = wrapper.find(selector);
      element.simulate('change', { target: { value } });
    };

    beforeEach(() => {
      change('input#amount', 0);
      change('select#from', '');
      change('select#to', '');
    });

    it('succeeds', done => {
      Currencies.convert.mockName('convert').mockResolvedValue('85.00');
      change('input#amount', 100);
      change('select#from', 'EUR');
      change('select#to', 'GBP');

      setTimeout(() => {
        wrapper.update();

        const request = { amount: 100, from: 'EUR', to: 'GBP' };
        expect(Currencies.convert).toBeCalledWith(request);

        const conversion = wrapper.find('#conversion').text();
        expect(conversion).toBe('85.00 GBP');
        expect(showErrors).toBeCalledWith([]);
        done();
      }, 100);
    });

    it('fails', done => {
      const errors = [{ message: 'error' }];
      Currencies.convert.mockName('convert').mockRejectedValue(errors);
      change('input#amount', 100);
      change('select#from', 'EUR');
      change('select#to', 'GBP');

      setTimeout(() => {
        wrapper.update();

        const request = { amount: 100, from: 'EUR', to: 'GBP' };
        expect(Currencies.convert).toBeCalledWith(request);

        const conversion = wrapper.find('#conversion').text();
        expect(conversion).toBe('');
        expect(showErrors).toBeCalledWith(errors);
        done();
      }, 100);
    });
  });
});
