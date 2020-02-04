import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'textTruncate'
})
export class TextTruncatePipe implements PipeTransform {

  transform(value: string, limit: number = 25, completeWords: boolean = false, elipsis: string = '...'): any {
    if(!value) {
      return '';
    }

    if(!completeWords) {
      limit = value.substr(0, limit).lastIndexOf(' ');
    }

    if(value.length > limit) {
      let tempLimit = limit - elipsis.length;
      return `${value.substr(0, tempLimit)}${elipsis}`
    } else {
      return value;
    }
  }

}
