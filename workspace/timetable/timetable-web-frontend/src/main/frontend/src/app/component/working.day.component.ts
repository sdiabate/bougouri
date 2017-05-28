import { WorkingDay } from '../model/working.day.model';
import { TimeInterval } from '../model/time.interval.model';
import { TimeIntervalComponent } from '../component/time.interval.component';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'working-day',
  templateUrl: '../template/working.day.html',
  styleUrls: ['../app.component.css'],
})

export class WorkingDayComponent {
    private workingDay: WorkingDay;

    private workingTimes = new Array<TimeInterval>(0);
    
    public addWorkingTime(): void {
        this.workingTimes.push(new TimeInterval());
    }
    
    public removeWorkingTime(timeInterval: TimeInterval): void {
        let index = this.workingTimes.indexOf(timeInterval);
        if(index > -1) {
            this.workingTimes.splice(index, 1);
        }
    }
    
    ngOnInit() {
        this.addWorkingTime();
    }
}
