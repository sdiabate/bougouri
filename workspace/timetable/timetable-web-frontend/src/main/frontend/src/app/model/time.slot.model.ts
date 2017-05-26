export class TimeSlot {
  startTime: number;
  endTime: number;
  selected: boolean;
  
  constructor (startTime: number, endTime: number) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.selected = true;
  }
    
}
