# onMark - a simple markdown editor

## About
onMark is a markdown editor letting you write and edit files with real-time markdown preview
onMark is written in Java using the JavaFX GUI framework, with plans of a version written in C/C++ using WxGTK

**this project is still in relatively early development. bug reports are welcome!**

### Progress tracker
- [x] file handling
- [x] basic text editor functionality
- [x] edit menu/button functionality
- [x] editor interface
- [x] more editor functionality!
- [x] markdown rendering
- [ ] recent files
- [ ] zoom
- [ ] **Accessibility options**, settings/preferences
- [ ] themes
- [ ] rich editor / editor switching

## Installation
**you must have the JFX binaries installed to compile this program. i use azul systems' jdk with bundled JFX but there are other options as well**

clone this repository:  
```
git clone https://codeberg.org/kameleonnn/onMark.git
```  

in the cloned repository install using maven

please enjoy using the program!

### Credits
this project uses [commonmark-java](https://github.com/commonmark/commonmark-java) for markdown parsing and HTML rendering
