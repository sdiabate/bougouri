export class TimeSlot {
  startTime: Date;
  endTime: Date;
  selected: boolean;
  
  constructor (startTime: Date, endTime: Date) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.selected = true;
  }
    
}
