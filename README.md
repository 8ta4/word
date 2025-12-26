# word

## Her Words, Not Mine

> What's `word`?

`word` is a Neovim plugin that generates sentence-by-sentence rewrites.

## Setup

> How do I set up `word`?

1. Make sure you're using [`lazy.nvim`](https://github.com/folke/lazy.nvim).

1. Open a terminal.

1. Add the following block to your `lazy.nvim` configuration:

   ```lua
   {
     "8ta4/word",
     keys = {
       { "<M-d>", function() require('word').confirm() end, mode = {"n", "i", "v"} },
       { "<M-f>", function() require('word').clear() end, mode = {"n", "i", "v"} },
       { "<M-j>", function() require('word').apply(2) end, mode = {"n", "i", "v"} },
       { "<M-k>", function() require('word').apply(1) end, mode = {"n", "i", "v"} },
       { "<M-s>", function() require('word').suggest() end, mode = {"n", "i", "v"} },
     },
   }
   ```

1. Run this command:
   ```bash
   mkdir -p ~/.config/word/
   ```

1. Copy an API key from the Groq website.

1. Run this command:
   ```bash
   pbpaste > ~/.config/word/groq
   ```
