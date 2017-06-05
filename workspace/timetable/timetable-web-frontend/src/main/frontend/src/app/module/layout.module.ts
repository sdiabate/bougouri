import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HeaderComponent } from '../component/app.header.component';

@NgModule({
  declarations: [HeaderComponent],
  imports: [BrowserModule, FormsModule, ReactiveFormsModule],
  exports: [HeaderComponent],
  providers: [],
  bootstrap: [HeaderComponent]
})
export class LayoutModule { }
