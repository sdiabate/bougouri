import { TimetablePage } from './app.po';

describe('timetable App', () => {
  let page: TimetablePage;

  beforeEach(() => {
    page = new TimetablePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
