export class User {
  id: number;
  login: string;
  firstname: string;
  lastname: string;
  description: string;
  profile: string;
  
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
