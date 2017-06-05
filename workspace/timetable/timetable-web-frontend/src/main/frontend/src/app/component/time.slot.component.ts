import { TimeSlot } from '../model/time.slot.model';
import { Component } from '@angular/core';

@Component({
  selector: 'time-slot',
  templateUrl: '../template/time.slot.html',
  styleUrls: ['../css/app.component.css']
})
export class TimeSlotComponent {
  private timeSlot: TimeSlot = new TimeSlot(new Date(), new Date());
  
  public select(): void {
    this.timeSlot.selected = !this.timeSlot.selected;
    console.log(this.timeSlot.selected);
  }

}
