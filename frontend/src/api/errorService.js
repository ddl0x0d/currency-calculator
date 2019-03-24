export const extractMessages = e => {
  if (e.response && e.response.data && e.response.data.errors) {
    return e.response.data.errors;
  }
  return [{ message: e.message }];
};
