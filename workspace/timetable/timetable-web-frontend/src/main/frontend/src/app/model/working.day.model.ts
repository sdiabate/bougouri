import { TimeSlot } from './time.slot.model';
import { Weekday } from './weekday.model';

export class WorkingDay {
  weekday: Weekday;
  timeSlots: TimeSlot[];
}
