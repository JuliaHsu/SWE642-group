import { Component, OnInit,Input } from '@angular/core';
import{SurveyService} from '../survey-service.service'
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'show-survey-list',
  templateUrl: './show-survey-list.component.html'
})
export class ShowSurveyListComponent implements OnInit {

  @Input() showMePartially: boolean;
  restUrl:string = "http://localhost:8080/RestfulWS/rest/studentSurvey/"
  surveys: SurveyService[]
  constructor(private http:HttpClient) { }

  ngOnInit() { 
    this.getSurveyAll().subscribe(
      res=>{
        this.surveys = res["body"];
        
        // for (let survey of this.surveys) {
        //   alert(survey.studentId); // 1, "string", false
        // }
      },
      error=>{
        this.surveys = [{
          fullName: 'sss',
          studentId: '111',
          address: 'test',
          city: 'test',
          state: 'test',
          zip: 'test',
          phone: 'test',
          email: 'test',
          url: 'test',
          todayDate: 'test',
          likeMostArr: [false,false,false,false,false,false],
          likeMost: 'test',
          hearFrom: 'test',
          gradMonth: 'test',
          gradYear: 'test',
          recommend: 'test',
          feedback: 'test',
        }]
      }// alert("not able to request the content'")
      // error => console.error('There was an error!', error)
    )
  }
  
  getSurveyAll():Observable<any>{
    return this.http.get(this.restUrl, {observe:'response'});
  }

}
