#
# $Id: Makefile 2795 2007-09-23 19:35:53Z dmz $
#

# include ../Makefile.defs

paper =		paper

talk = 		talk

texfiles =	$(wildcard *.tex)

dvifiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.dvi,$(texfile)))

psfiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.ps,$(texfile)))

pdffiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.pdf,$(texfile)))

.PHONY: all talk archive proof

all:	${paper}.pdf ${talk}.pdf

talk: 	${talk}.ps ${talk}.pdf

${paper}.pdf:	${paper}.tex
	export BIBINPUTS=".:/Users/josr/Sandboxes/svn/bibliography//"; \
	export BSTINPUTS=".:/Users/josr/Sandboxes/svn/latex//:/Users/josr/Sandboxes/mobius_inria//::LNILaTeX-Vorlage//"; \
	export TEXINPUTS=".:/Users/josr/Sandboxes/svn/latex//:/Users/josr/Sandboxes/mobius_inria/TexInputs//::LNILaTeX-Vorlage"; \
	pdflatex ${paper} && pdflatex ${paper} && bibtex ${paper} && pdflatex ${paper} && pdflatex ${paper}

${talk}.pdf:	${talk}.tex
	export BIBINPUTS=".//:$(BIBDIR)//:"; \
	export BSTINPUTS=".//:$(LATEXDIR)//:"; \
	export TEXINPUTS=".//:$(LATEXDIR)//:"; \
	rubber --pdf ${talk}

view:	${paper}.pdf
	open -a Skim.app ${paper}.pdf

# Check style:
proof:
	echo "weasel words: "
	sh ~/bin/weasels.sh *.tex
	echo
	echo "passive voice: "
	sh ~/bin/passive.sh *.tex
	echo
	echo "duplicates: "
	perl ~/bin/dups.pl *.tex

cleanup:
	rm -f _region_* *.aux *.log *# *~

clean:
	rubber --pdf --clean ${paper}
	rubber --pdf --clean ${talk}
