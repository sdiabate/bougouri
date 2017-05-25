import { WorkingDay } from '../model/working.day.model';
import { Component } from '@angular/core';

@Component({
  selector: 'time-slot',
  templateUrl: '../template/working.day.html',
  styleUrls: ['../app.component.css']
})

export class WorkingDayComponent {
  private workingDay: WorkingDay;
}
