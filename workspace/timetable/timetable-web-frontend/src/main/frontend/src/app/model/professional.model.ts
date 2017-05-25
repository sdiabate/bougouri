import { User } from './user.model';
export class Professional extends User {
  specialty: string;
  address: string;
  mobilePhone: string;
  landLine: string;
  email: string;
  
  constructor(values: Object = {}) {
    super(values);
  }
}
