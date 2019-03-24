import Errors from './Errors';
import React from 'react';
import { mount } from 'enzyme';

describe('<Errors />', () => {
  const errors = messages => <Errors messages={messages} />;

  it('renders correctly without errors', () => {
    const wrapper = mount(errors([]));
    expect(wrapper).toMatchSnapshot();
  });

  it('renders correctly with errors', () => {
    const messages = [
      { field: 'Field', message: 'has error' },
      { message: 'There is an error' }
    ];
    const wrapper = mount(errors(messages));
    expect(wrapper).toMatchSnapshot();
  });
});
