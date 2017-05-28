import { TimeInterval } from '../model/time.interval.model';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'time-interval',
  templateUrl: '../template/time.interval.html',
  styleUrls: ['../app.component.css']
})

export class TimeIntervalComponent {
  @Input()
  private timeInterval = new TimeInterval();
}
