# Item Data

## Summary

### Issue
An item represents a concept that contains multiple related pieces of knowledge. An example item may be a vocabulary word, where the pieces of knowledge are the word in the user's native language and a translation into a foreign language.

Items should represent a collection of knowledge and not dictate a particular layout or learning strategy in the context of a flashcard application. Data must be stored in such a way that cards using separate layouts may be defined to present the knowledge in various ways.

For example, consider the English word "Japan". This item may have three separate pieces of knowledge:

* English: Japan
* Hiragana: にほん
* Kanji: 日本

The user may want to create two separate cards for this item. Both would place the English word "Japan" on the front, but one card would contain the hiragana equivalent on the back and the other would include the kanji on the back.

### Decision

Since the pieces of knowledge may vary depending on the item itself, it has been decided that this information will be stored in a JSON blob in the items table. The layouts themselves will contain HTML templates with handlebars-style replacements to reference the knowledge for a particular item.

```json
{
  "fields": {
    "eng": {
      "value": "Japan",
      "defaultDisplay": "front"
    },
    "hiragana": {
      "value": "にほん",
      "defaultDisplay": "back"
    },
    "kanji": {
      "value": "日本",
      "defaultDisplay": "back"
    }
  }
}
```