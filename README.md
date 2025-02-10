# Interview Quiz

This assignment is an application to retrieve information about financial institution holiday.

---

The service provides 3 APIs to get holiday data

- The first one is an API to get all holiday in year from parameter 'year'.
```
path: /holiday
method: GET
params: year (optional)
```

Example response:
```json
[
   {
      "HolidayWeekDay": "Monday",
      "HolidayWeekDayThai": "วันจันทร์",
      "Date": "2023-01-02",
      "DateThai": "02/01/2566",
      "HolidayDescription": "Substitution for New Year's Eve and New Year's Day (Saturday 31st  December 2022 and Saturday 1st January 2023)",
      "HolidayDescriptionThai": "ชดเชยวันสิ้นปีและวันขึ้นปีใหม่ (วันเสาร์ที่ 31 ธันวาคม 2565 และวันอาทิตย์ที่ 1 มกราคม 2566)"
   },
   {
      "HolidayWeekDay": "Monday",
      "HolidayWeekDayThai": "วันจันทร์",
      "Date": "2023-03-06",
      "DateThai": "06/03/2566",
      "HolidayDescription": "Makha Bucha Day",
      "HolidayDescriptionThai": "วันมาฆบูชา"
   },
   ...
]
```

- The second API is to find the nearest holiday in the future from now.
```
path: /holiday/nearest
method: GET
params: none
```

Example response:
```json
{
   "HolidayWeekDay": "Monday",
   "HolidayWeekDayThai": "วันจันทร์",
   "Date": "2023-01-02",
   "DateThai": "02/01/2566",
   "HolidayDescription": "Substitution for New Year's Eve and New Year's Day (Saturday 31st  December 2022 and Saturday 1st January 2023)",
   "HolidayDescriptionThai": "ชดเชยวันสิ้นปีและวันขึ้นปีใหม่ (วันเสาร์ที่ 31 ธันวาคม 2565 และวันอาทิตย์ที่ 1 มกราคม 2566)"
}
```

- The last API will find all holiday in range of date.
```
path: /holiday/range
method: GET
params: startDate(format is yyyy-MM-dd), endDate(format is yyyy-MM-dd)
```

Example response:
```json
[
   {
      "HolidayWeekDay": "Monday",
      "HolidayWeekDayThai": "วันจันทร์",
      "Date": "2023-01-02",
      "DateThai": "02/01/2566",
      "HolidayDescription": "Substitution for New Year's Eve and New Year's Day (Saturday 31st  December 2022 and Saturday 1st January 2023)",
      "HolidayDescriptionThai": "ชดเชยวันสิ้นปีและวันขึ้นปีใหม่ (วันเสาร์ที่ 31 ธันวาคม 2565 และวันอาทิตย์ที่ 1 มกราคม 2566)"
   },
   {
      "HolidayWeekDay": "Monday",
      "HolidayWeekDayThai": "วันจันทร์",
      "Date": "2023-03-06",
      "DateThai": "06/03/2566",
      "HolidayDescription": "Makha Bucha Day",
      "HolidayDescriptionThai": "วันมาฆบูชา"
   },
   ...
]
```

### Tasks

You have to do all the tasks to complete the assignment.

1. Create a web client to retrieve data from the public API.
2. Using the HolidayService to generate data from public API.
3. Verify that the results of all APIs are as expected.

Note:
- Fix some issues that may occur when implementing the API.
- All unit tests should be passed and coverage is 100%.
- You can you everything to improve performance such as cache.
- Push the project to your personal git and set it to public (we will see how you work by the commits).

---

### Public API Documents

https://apiportal.bot.or.th/bot/public/node/104
