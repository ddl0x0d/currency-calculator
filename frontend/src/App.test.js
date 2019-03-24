import App from './App';
import React from 'react';
import { mount, shallow } from 'enzyme';

describe('<App />', () => {
  it('renders without crashing', () => {
    mount(<App />);
  });

  it('renders correctly', () => {
    const wrapper = shallow(<App />);
    expect(wrapper).toMatchSnapshot();
  });
});
