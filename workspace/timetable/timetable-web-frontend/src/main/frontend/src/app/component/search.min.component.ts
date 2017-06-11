import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'search-min',
  templateUrl: '../template/search.min.html',
  styleUrls: ['../css/app.component.css', '../css/left.component.css']
})
export class SearchComponent {
    
    private expression: string;

    search(): void {
        console.log(this.expression);
    }
}
