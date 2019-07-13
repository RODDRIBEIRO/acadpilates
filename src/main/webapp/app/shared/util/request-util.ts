import { HttpParams } from '@angular/common/http';
import { Params } from '@angular/router';

const KEYWORDS = ['page', 'sort', 'query', 'search'];

function isNumeric(n: any): boolean {
  return !(isNaN(Number(n)) || isNaN(parseFloat(n)));
}

export const createRequestOption = (req?: any): HttpParams => {
  let options: HttpParams = new HttpParams();
  if (req) {
    Object.keys(req).forEach(key => {
      if (key !== 'sort') {
        if (key === 'query') {
          if (typeof req.query === 'string') {
            options = options.set(key, req[key]);
          } else {
            Object.keys(req.query).forEach(qKey => {
              if (req.query[qKey]) {
                options = options.set(qKey, req.query[qKey]);
              }
            });
          }
        } else {
          options = options.set(key, req[key]);
        }
      }
    });
    if (req.sort) {
      req.sort.forEach(val => {
        options = options.append('sort', val);
      });
    }
  }
  return options;
};

export const castToObject = (param?: Params): any => {
  const obj = {};
  Object.keys(param).forEach(key => {
    if (!KEYWORDS.includes(key)) {
      // console.log(key, param[key], isNumeric(param[key]));
      obj[key] = isNumeric(param[key]) ? Number(param[key]) : param[key];
    }
  });
  return obj;
};

export const castToQuery = (req?: any): any => {
  if (req) {
    if (req.search) {
      Object.keys(req.search).forEach(key => {
        if (req.search[key]) {
          req[key] = req.search[key];
        }
      });
      delete req.search;
    } else {
      Object.keys(req).forEach(key => {
        if (!req[key]) {
          delete req[key];
        }
      });
    }
    return req;
  }
  return '';
};
