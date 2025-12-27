# word

## Her Words, Not Mine

> What's `word`?

`word` is a Neovim plugin that generates sentence-by-sentence rewrites.

## Setup

> How do I set up `word`?

1. Make sure you're using a Mac.

1. Make sure you're using [Alacritty](https://github.com/alacritty/alacritty).

1. Make sure you're using [`lazy.nvim`](https://github.com/folke/lazy.nvim).

1. Open a terminal.

1. Merge these bindings into your Alacritty configuration:

   ```toml
   [keyboard]
   bindings = [
     { chars = "\u001bd", key = "D", mods = "Command" },
     { chars = "\u001bf", key = "F", mods = "Command" },
     { chars = "\u001bj", key = "J", mods = "Command" },
     { chars = "\u001bk", key = "K", mods = "Command" },
     { chars = "\u001bs", key = "S", mods = "Command" },
   ]
   ```

1. Add this block to your `lazy.nvim` configuration:

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
     opts = {
       styles = {
         { name = "casual", prompt = "Use a casual tone. Use correct grammar. Check if the sentence meets these requirements. Explain any failures. Give two rewrites of the sentence. Do not rewrite the context." },
       },
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

## Usage

> How do I get suggestions?

Press `⌘ + s`. Think suggest.

> How do I apply the first suggestion?

Press `⌘ + k`. Vim uses k for up.

> How do I apply the second suggestion?

Press `⌘ + j`. Vim uses j for down.

> How do I mark a sentence as reviewed?

Press `⌘ + d`. Think done.

> How do I remove the reviewed status?

Press `⌘ + f`. Think forget.

> Can I add more styles?

Yes.

1. Add the number keybindings to your Alacritty configuration:

   ```toml
   [keyboard]
   bindings = [
     { chars = "\u001b1", key = "Key1", mods = "Command" },
     { chars = "\u001b2", key = "Key2", mods = "Command" },
     { chars = "\u001bd", key = "D", mods = "Command" },
     { chars = "\u001bf", key = "F", mods = "Command" },
     { chars = "\u001bj", key = "J", mods = "Command" },
     { chars = "\u001bk", key = "K", mods = "Command" },
     { chars = "\u001bs", key = "S", mods = "Command" },
   ]
   ```

1. Add the number keybindings and the second style to your `lazy.nvim` configuration:

   ```lua
   local base = " Use correct grammar. Check if the sentence meets these requirements. Explain any failures. Give two rewrites of the sentence. Do not rewrite the context."

   {
     "8ta4/word",
     keys = {
       { "<M-1>", function() require('word').style(1) end, mode = {"n", "i", "v"} },
       { "<M-2>", function() require('word').style(2) end, mode = {"n", "i", "v"} },
       { "<M-d>", function() require('word').confirm() end, mode = {"n", "i", "v"} },
       { "<M-f>", function() require('word').clear() end, mode = {"n", "i", "v"} },
       { "<M-j>", function() require('word').apply(2) end, mode = {"n", "i", "v"} },
       { "<M-k>", function() require('word').apply(1) end, mode = {"n", "i", "v"} },
       { "<M-s>", function() require('word').suggest() end, mode = {"n", "i", "v"} },
     },
     opts = {
       styles = {
         { name = "casual", prompt = "Use a casual tone." .. base },
         { name = "formal", prompt = "Use a formal tone." .. base },
       },
     },
   }
   ```

> How do I select the second style in the configuration?

Press `⌘ + 2`. It picks the second entry in your `styles` list.
