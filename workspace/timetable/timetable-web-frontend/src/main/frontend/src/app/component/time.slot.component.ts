import { TimeSlot } from '../model/time.slot.model';
import { Component } from '@angular/core';

@Component({
  selector: 'time-slot',
  templateUrl: '../template/time.slot.html',
  styleUrls: ['../app.component.css']
})

export class TimeSlotComponent {
  private timeSlot: TimeSlot;
}
