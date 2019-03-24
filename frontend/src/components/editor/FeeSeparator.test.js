import FeeSeparator from './FeeSeparator';
import { mount } from 'enzyme';
import React from 'react';

describe('<FeeSeparator />', () => {
  it('renders correctly', () => {
    const wrapper = mount(<FeeSeparator />);
    expect(wrapper).toMatchSnapshot();
  });
});
