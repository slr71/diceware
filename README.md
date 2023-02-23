# diceware

A small Clojure project for generating Diceware passwords.

## Usage

This utility generates random passwords with a configurable separator and a configurable number of words.

```
$ java -jar diceware-0.1.0-standalone.jar [options]
```

## Options

### Number of Words

The `-w` or `--num-words` option allows the caller to specify the number of words to include in each generated
password. The default number of words in the password is 4.

### Number of Passwords

The `-n` or `--num-passwords` option allows the caller to specify the number of passwords to generate. A single password
will be generated if this option isn't specified.

### Separator

The `-s` or `--separator` option allows the caller to specify the separator to place between words in generated
passwords. An asterisk will be used if this option is left unspecified.

### Word List URL

The `-u` or `--word-list-url` option allows the caller to specify a URL that can be used to retrieve the list of words
to use when generating passwords. The [Diceware word list from 2016-07-18][1] will be used by default.

### Getting Help

The `-h` or `--help` option causes the utility to display a brief help message and exit.
