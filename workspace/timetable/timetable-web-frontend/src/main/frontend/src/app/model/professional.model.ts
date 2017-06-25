import { User } from './user.model';
import { WorkingDay } from './working.day.model';
import { Weekday } from './Weekday.model';

export class Professional extends User {
    specialty: string;
    address: string;
    mobilePhone: string;
    landLine: string;
    email: string;
    workingDays: WorkingDay[] = [];

    constructor(values: Object = {}) {
        super(values);
        for(let weekday in Weekday) {
            let workingDay: WorkingDay = new WorkingDay();
            workingDay.weekday = WorkingDay[weekday];
            this.workingDays.push(workingDay);
        }
    }
}
